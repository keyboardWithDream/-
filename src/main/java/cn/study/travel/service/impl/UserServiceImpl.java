package cn.study.travel.service.impl;

import cn.study.travel.dao.UserDao;
import cn.study.travel.dao.impl.UserDaoImpl;
import cn.study.travel.domain.User;
import cn.study.travel.service.UserService;

/**
 * @author Harlan
 * @date 2020/7/24 14:57
 */
public class UserServiceImpl implements UserService {
    UserDao dao = new UserDaoImpl();

    @Override
    public boolean register(User user) {
        User result = dao.findByUsername(user.getUsername());
        if (result == null){
            dao.save(user);
            return true;
        }else {
            return false;
        }
    }
}
