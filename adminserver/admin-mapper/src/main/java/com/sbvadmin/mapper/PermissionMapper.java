package com.sbvadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sbvadmin.model.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/15 11:45
 */
@Mapper
public interface PermissionMapper  extends BaseMapper<Permission> {
    /*
     * Notes:  获得所有权限点
     * @param: []
     * @return: java.util.List<com.sbvadmin.model.Permission>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/7/19 20:20
     **/
    List<Permission> getAllPermissions();

    /**
     * Notes:  获取当前登录用户的所有权限点
     * @param: 用户id
     * @return: java.util.List<com.sbvadmin.model.Permission>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/9/7 20:32
     **/
    List<Permission> getPermissionsByUid(Long id);
}
