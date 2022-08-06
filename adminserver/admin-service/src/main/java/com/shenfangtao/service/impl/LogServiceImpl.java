package com.shenfangtao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenfangtao.mapper.LogMapper;
import com.shenfangtao.model.Log;
import com.shenfangtao.service.LogService;
import org.springframework.stereotype.Service;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/8/2 17:06
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {
}
