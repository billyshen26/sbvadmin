package com.sbvadmin.mapper;

import com.sbvadmin.model.Dept;
import com.sbvadmin.model.UserDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户机构 Mapper 接口
 * </p>
 *
 * @author billy
 * @since 2023-03-03
 */
@Mapper
public interface UserDeptMapper extends BaseMapper<UserDept> {
    @Select("select sys_dept.* from sys_dept, sys_user_dept where sys_user_dept.uid = #{uid} and sys_dept.id = sys_user_dept.did")
    List<Dept> returnDepts(@Param("uid") String id);
    @Select("select sys_dept.id from sys_dept, sys_user_dept where sys_user_dept.uid = #{uid} and sys_dept.id = sys_user_dept.did")
    List<Long> returnDeptIds(@Param("uid") String id);
}
