package com.shenfangtao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenfangtao.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/12 20:09
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
