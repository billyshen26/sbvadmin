package com.sbvadmin.service.impl;

import com.sbvadmin.model.Log;
import com.sbvadmin.mapper.LogMapper;
import com.sbvadmin.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author billy
 * @since 2022-11-08
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
