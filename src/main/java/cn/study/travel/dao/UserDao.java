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

    /**
     * 通过用户唯一码查询用户
     * @param code 唯一编码
     * @return 用户
     */
    public User findByCode(String code);

    /**
     *更新用户的激活状态
     * @param user 用户
     */
    public void updateStatus(User user);

    /**
     * 通过用户名和密码查询用户
     * @param name 用户名
     * @param password 密码
     * @return 用户对象
     */
    public User findUserByUsernameAndPassword(String name, String password);
}
