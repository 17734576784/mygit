/**
 * 
 */
package com.pile.mapper;
import java.util.List;
import java.util.Map;
/**   
 * @ClassName:  PileMapper   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: dbr 
 * @date:   2018年7月28日 上午9:45:59   
 *      
 */
public interface PileMapper {
	// 获取指定充电桩状态
		List<Map<String, Object>> getPileState(String PileNo);

		// 获取指定充电桩Gps
		Map<String, Object> getPileGps(String PileNo);
		
		// 获取指定充电桩的终端编号
		Integer getRtuByPile(String PileNo);
		
		Map<String,Object> getPileRate(String PileNo);
}
