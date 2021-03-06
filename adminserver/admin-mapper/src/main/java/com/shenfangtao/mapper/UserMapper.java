package com.shenfangtao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenfangtao.model.Role;
import com.shenfangtao.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/12 20:09
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * Notes:
     * @param: 根据用户id，获得他所有的角色
     * @return: java.util.List<com.imaodu.iot.model.Role>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/7/15 11:36
     **/
    List<Role> getUserRolesByUid(BigInteger id);

    /**
     * Notes:  获取用户列表，及其包含的角色信息
     * @param: []
     * @return: java.util.List<com.shenfangtao.model.User>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/7/15 16:33
     **/
    List<User> getUsersWithRoles();
}
