package com.sbvadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sbvadmin.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/8/31 13:47
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * Notes:  补充机构id
     * @param: [did, name, status]
     * @return: java.util.List<com.sbvadmin.model.Role>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/5/27 19:54
     **/
    List<Role> getRolesWithPermissions(Long did, String name, String status);
}
