/**
 * 
 */
package com.wo.mapper;

import java.util.Map;

/**
 * @author dbr
 * 
 */
public interface InterceptorMapper {
	// 判断充电桩是否属于该合作伙伴
	Boolean isPileOfPartner(Map<String, Object> param);

	// 判断充电桩是否属于该合作伙伴
	Boolean isStationOfPartner(Map<String, Object> param);

}
