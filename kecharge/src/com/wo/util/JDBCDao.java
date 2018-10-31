package com.wo.util;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/** 执行更新操作语句 */
	public boolean update(String sql) {
		if (sql == null || sql.isEmpty()) {
			return false;
		}

		int rows = 0;
		try {
			rows = this.jdbcTemplate.update(sql);
			if (rows > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	/** 执行sql语句，可执行插入、更新、查询、删除等操作 */
	public boolean executeSql(String sql) {
		if (sql == null || sql.isEmpty()) {
			return false;
		}
 
		try {
			this.jdbcTemplate.execute(sql);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/** 可执行普通sql,也可执行存储过程{call procedure(param1,param1...)},返回多条数据 */
	public List<Map<String, Object>> query(String sql) {
		if (sql == null || sql.isEmpty()) {
			return null;
		}

		List<Map<String, Object>> list = null;
		try {
			list = this.jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
		}
		return list;
	}
	
	/** 可执行普通sql,也可执行存储过程{call procedure(param1,param1...)},返回一条数据 */
	public Map<String, Object> queryOne(String sql) {
		if (sql == null || sql.isEmpty()) {
			return null;
		}

		Map<String, Object> map = null;
		try {
			map = this.jdbcTemplate.queryForMap(sql);
		} catch (Exception e) {
		}
		return map;
	}
	
	// 查询数据总条数
	public int queryTotalCount(String sql) {
		if (sql == null || sql.isEmpty()) {
			return 0;
		}

		List<Map<String, Object>> list = null;
		try {
			list = this.jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
		}
		return list.size();
	}	
}
