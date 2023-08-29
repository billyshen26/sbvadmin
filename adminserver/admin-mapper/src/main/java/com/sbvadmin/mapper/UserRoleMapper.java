package com.sbvadmin.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.sbvadmin.model.User;
import com.sbvadmin.model.UserRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author billy
 * @since 2022-09-20
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * Notes:  获取User详细信息 TIPS: 一对一用法
     * @param: [queryWrapper]
     * @return: java.util.List<com.sbvadmin.model.UserRole>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/8/29 19:50
     **/
    @Override
    @Results(value = {
            @Result(property = "id", column = "id", id = true),
            @Result(column = "uid", property = "uid"),//必须要加，否则UserRole实体中uid为null
            @Result(property = "user", column = "uid",
                    javaType = User.class,
                    one = @One(select = "com.sbvadmin.mapper.UserMapper.selectById")
            )
    })
    @Select("select * from sys_user_role ${ew.customSqlSegment}") // 这个必须加，否则无法获取user信息
    List<UserRole> selectList(@Param(Constants.WRAPPER) Wrapper<UserRole> queryWrapper);
}
