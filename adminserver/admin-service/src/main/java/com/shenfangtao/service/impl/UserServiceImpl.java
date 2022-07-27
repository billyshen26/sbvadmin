package com.shenfangtao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenfangtao.mapper.UserMapper;
import com.shenfangtao.model.User;
import com.shenfangtao.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/13 17:53
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
        if (user == null){
            throw new UsernameNotFoundException("账户不存在");
        }
        user.setRoles(userMapper.getUserRolesByUid(user.getId()));
        System.out.println(user);
        return user;
    }

    /**
     * Notes:  获得所有用户列表
     * @param: []
     * @return: java.util.List<com.shenfangtao.model.User>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/7/18 22:38
     **/
    public List<User> getUsersWithRoles() {
        return userMapper.getUsersWithRoles();
    }


    @Override
    public boolean save(User entity) {
        rabbitTemplate.convertAndSend("add-user", "add"+entity.getName());
        return super.save(entity);
    }
}
