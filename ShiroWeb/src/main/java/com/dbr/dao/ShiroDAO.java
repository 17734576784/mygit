/**
 * 
 */
package com.dbr.dao;

/**   
 * @ClassName:  ShiroDAO   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: dbr 
 * @date:   2018年9月8日 上午8:43:21   
 *      
 */
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ShiroDAO {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 根据用户名查询密码
     */
    public String getPasswordByUserName(String username) {
        String sql = "select PASSWORD from user where name = ?";
        String password = jdbcTemplate.queryForObject(sql, String.class, username);
        return password;
    }

    /**
     * 查询当前用户对应的权限
     */
    public List<String> getPermissionByUserName(String username) {
        String sql = "select P.permission from permission P inner join role R on P.ROLE_id=R.id INNER JOIN user u ON r.user_id = u.id where u.name =  ?";
        List<String> perms = jdbcTemplate.queryForList(sql, String.class, username);
        return perms;
    }
}
