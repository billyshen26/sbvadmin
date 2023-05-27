package com.sbvadmin.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.sbvadmin.model.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 机构管理 Mapper 接口
 * </p>
 *
 * @author billy
 * @since 2023-03-02
 */
public interface DeptMapper extends BaseMapper<Dept> {
    String wrapperSql = "select sys_dept.*,sys_user_dept.uid from sys_dept left join sys_user_dept on sys_dept.id = sys_user_dept.did ${ew.customSqlSegment}";

    @Override
    @Select(wrapperSql)
    List<Dept> selectList(@Param("ew") Wrapper<Dept> queryWrapper);

    List<Dept> getAllDepts();
}
