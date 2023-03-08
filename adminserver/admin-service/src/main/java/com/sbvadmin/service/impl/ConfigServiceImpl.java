package com.sbvadmin.service.impl;

import com.sbvadmin.model.Config;
import com.sbvadmin.mapper.ConfigMapper;
import com.sbvadmin.service.IConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 配置 服务实现类
 * </p>
 *
 * @author billy
 * @since 2023-03-02
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

}
