/**   
* @Title: ScheduleFactory.java 
* @Package com.nb.configuration 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年3月12日 上午10:01:26 
* @version V1.0   
*/
package com.nb.configuration;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.model.ScheduleJob;
import com.nb.service.IScheduleService;

/**
 * @ClassName: ScheduleFactory
 * @Description: 自动任务管理
 * @author dbr
 * @date 2019年3月12日 上午10:01:26
 * 
 */
@Component
@Configuration
@EnableScheduling
public class ScheduleFactory {
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	@Autowired
	private IScheduleService scheduleService;

	// 当前Trigger使用的
	private Map<String, String> jobUniqueMap = new HashMap<String, String>();

	// TODO 此处暂且注释，后续有后台定时任务逻辑 开启
	@SuppressWarnings("unlikely-arg-type")
	@Scheduled(fixedRate = 1000 * 60 * 5) // 每隔5分钟查库，并根据查询结果决定是否重新设置定时任务
	public void scheduleUpdateCronTrigger() throws Exception {
		try {
			System.out.println("fixedRate : " + LocalDateTime.now());
			// schedulerFactoryBean 由spring创建注入
			Scheduler scheduler = schedulerFactoryBean.getScheduler();

			// 获取最新删除(禁用)任务列表，将其从调度器中删除，并且从jobUniqueMap中删除
			List<ScheduleJob> jobDelList = scheduleService.findDelJobList();
			for (ScheduleJob delJob : jobDelList) {
				JobKey jobKey = JobKey.jobKey(delJob.getJobName(), delJob.getJobGroup());
				scheduler.deleteJob(jobKey);
				jobUniqueMap.remove(TriggerKey.triggerKey(delJob.getJobName(), delJob.getJobGroup()));
			}

			List<ScheduleJob> jobList = scheduleService.findLegalJobList();
			for (ScheduleJob job : jobList) {
				TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
				// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
				CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
				// 该job数据库中的Trigger表达式
				String dbCron = job.getCronExpression();
				// 不存在，创建一个
				if (null == trigger) {
					try {
						@SuppressWarnings("unchecked")
						Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(job.getQuartzClass());
						JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup())
								.build();
						jobDetail.getJobDataMap().put("scheduleJob", job);
						// 表达式调度构建器
						CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
						// 按新的cronExpression表达式构建一个新的trigger
						trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup())
								.withSchedule(scheduleBuilder).build();

						jobUniqueMap.put(triggerKey.toString(), trigger.getCronExpression());
						// currentCron = trigger.getCronExpression();

						scheduler.scheduleJob(jobDetail, trigger);
					} catch (Exception e) {
						e.printStackTrace();
						LoggerUtil.Logger(LogName.ERROR).equals(e.getMessage());
					}

				} else if (!jobUniqueMap.get(triggerKey.toString()).equals(dbCron)) { // Trigger已存在，那么更新相应的定时设置
					// 表达式调度构建器
					CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(dbCron);
					// 按新的cronExpression表达式重新构建trigger
					trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

					trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder)
							.build();
					// 按新的trigger重新设置job执行
					scheduler.rescheduleJob(triggerKey, trigger);

					jobUniqueMap.put(triggerKey.toString(), dbCron);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SchedulerFactoryBean getSchedulerFactoryBean() {
		return schedulerFactoryBean;
	}

	public void setSchedulerFactoryBean(SchedulerFactoryBean schedulerFactoryBean) {
		this.schedulerFactoryBean = schedulerFactoryBean;
	}
}
