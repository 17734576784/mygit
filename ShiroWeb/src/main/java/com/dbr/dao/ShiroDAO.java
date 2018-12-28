/**
 * 
 */
package com.dbr.dao;

/**   
 * @ClassName:  ShiroDAO   
 * @Description:TODO(������һ�仰��������������)   
 * @author: dbr 
 * @date:   2018��9��8�� ����8:43:21   
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
     * �����û�����ѯ����
     */
    public String getPasswordByUserName(String username) {
        String sql = "select PASSWORD from user where name = ?";
        String password = jdbcTemplate.queryForObject(sql, String.class, username);
        return password;
    }

    /**
     * ��ѯ��ǰ�û���Ӧ��Ȩ��
     */
    public List<String> getPermissionByUserName(String username) {
        String sql = "select P.permission from permission P inner join role R on P.ROLE_id=R.id INNER JOIN user u ON r.user_id = u.id where u.name =  ?";
        List<String> perms = jdbcTemplate.queryForList(sql, String.class, username);
        return perms;
    }
}
