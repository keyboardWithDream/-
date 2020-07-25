package cn.study.travel.service.impl;

import cn.study.travel.dao.UserDao;
import cn.study.travel.dao.impl.UserDaoImpl;
import cn.study.travel.domain.User;
import cn.study.travel.service.UserService;
import cn.study.travel.util.MailUtils;
import cn.study.travel.util.UuidUtil;

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
            user.setCode(UuidUtil.getUuid());
            user.setStatus("N");
            dao.save(user);
            String content = "<a href='http://localhost/travel/activeUserServlet?code="+ user.getCode() +"'>点击激活[旅游网]</a>";
            MailUtils.sendMail(user.getEmail(),content,"旅游网邮件激活");
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean activeUser(String code) {
        User result = dao.findByCode(code);
        if (result == null){
            return false;
        }else {
            dao.updateStatus(result);
            MailUtils.sendMail(result.getEmail(),"您以成功激活, 欢迎注册!", "账号激活成功");
            return true;
        }
    }
}
