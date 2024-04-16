package com.sbvadmin.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sbvadmin.model.Log;
import com.sbvadmin.model.Role;
import com.sbvadmin.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/12 20:09
 */
@Mapper
@CacheConfig(cacheNames = "user")
public interface UserMapper extends BaseMapper<User> {
    /**
     * Notes:
     * @param: 根据用户id，获得他所有的角色
     * @return: java.util.List<com.imaodu.iot.model.Role>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/7/15 11:36
     **/
    List<Role> getUserRolesByUid(Long id);

    /**
     * Notes:  获取用户列表，及其包含的角色信息
     * @param: 根据机构id,获取用户
     * @return: java.util.List<com.sbvadmin.model.User>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/7/15 16:33
     **/
    List<User> getUsersWithRoles(Long did,Long id,String name);

    /**
     * Notes:  用于登录
     * @param: [username]
     * @return: com.sbvadmin.model.User
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/5/27 19:39
     **/
    User getOwnUserWithRoles(String username);


    /**
     * Notes:  用于识别用户 getOwnUserWithRoles有局限，比如用户名被修改了就会出现问题
     * @param: [username]
     * @return: com.sbvadmin.model.User
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/5/27 19:39
     **/
    @Cacheable(key = "#root.args")
    User getOwnUserWithRolesByUid(Long uid);

    /**
     * Notes:  根据角色id获取所有用户
     * @param: [rid]
     * @return: java.util.List<com.sbvadmin.model.User>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/8/29 19:49
     **/
    @Select("select * from sys_user left join sys_user_role on sys_user.id = sys_user_role.uid " +
            "where sys_user_role.rid = #{rid}")
    List<User> getUsersByRoleId(Long rid);
}
