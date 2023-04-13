package com.sbvadmin.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbvadmin.model.Log;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author billy
 * @since 2022-11-08
 */
@Mapper
public interface LogMapper extends BaseMapper<Log> {
    // TIPS: 链表查询
    String wrapperSql = "select sys_log.*,sys_user.nickname from sys_log left join sys_user on sys_log.uid = sys_user.id ${ew.customSqlSegment}";

    @Override
    @Select(wrapperSql)
    <P extends IPage<Log>> P selectPage(P page, @Param("ew") Wrapper<Log> queryWrapper);

    @Override
    @Select(wrapperSql)
    Log selectOne(@Param("ew") Wrapper<Log> queryWrapper);

}
