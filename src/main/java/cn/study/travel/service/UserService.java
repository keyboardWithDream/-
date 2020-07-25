package cn.study.travel.service;

import cn.study.travel.domain.User;

/**
 * @author Harlan
 * @date 2020/7/24 14:57
 */
public interface UserService {

    /**
     * 用户注册
     * @param user 用户信息
     * @return 是否成功
     */
    public boolean register(User user);

    /**
     * 用户激活
     * @param code 激活码
     * @return 是否成功
     */
    public boolean activeUser(String code);
}
