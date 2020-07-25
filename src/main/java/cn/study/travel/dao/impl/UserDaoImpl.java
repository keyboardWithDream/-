package cn.study.travel.dao.impl;

import cn.study.travel.dao.UserDao;
import cn.study.travel.domain.User;
import cn.study.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 * @author Harlan
 * @date 2020/7/24 14:57
 */
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUsername(String username) {
        User user = null;
        String sql = "SELECT * FROM tab_user WHERE username = ?";
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (Exception e) {

        }
        return user;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO tab_user(username, password, name, birthday, sex, telephone, email) values(?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail());
    }
}
