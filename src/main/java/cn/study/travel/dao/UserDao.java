package cn.study.travel.dao;

import cn.study.travel.domain.User;

/**
 * @author Harlan
 * @date 2020/7/24 14:57
 */
public interface UserDao {

    /**
     * 通过用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    public User findByUsername(String username);

    /**
     * 存储用户
     * @param user 用户信息
     */
    public void save(User user);
}
