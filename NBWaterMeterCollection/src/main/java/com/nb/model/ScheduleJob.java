/**   
* @Title: ScheduleJob.java 
* @Package com.nb.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年3月12日 上午10:28:00 
* @version V1.0   
*/
package com.nb.model;

/**
 * @ClassName: ScheduleJob
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dbr
 * @date 2019年3月12日 上午10:28:00
 * 
 */
public class ScheduleJob {

	private int jobId;// 任务ID
	private String jobName;// 任务名称
	private String jobGroup;// 任务分组
	private byte jobStatus;// 任务状态 0禁用 1启用
	private String cronExpression;// 任务运行时间表达式
	private String quartzClass;// 定时任务处理类
	private String description;// 描述信息

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public byte getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(byte jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getQuartzClass() {
		return quartzClass;
	}

	public void setQuartzClass(String quartzClass) {
		this.quartzClass = quartzClass;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

}
