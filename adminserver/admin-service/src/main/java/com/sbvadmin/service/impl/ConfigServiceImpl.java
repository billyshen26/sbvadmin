package com.sbvadmin.service.impl;

import com.sbvadmin.model.Config;
import com.sbvadmin.mapper.ConfigMapper;
import com.sbvadmin.service.IConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
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
    /**
     * Notes:  复写更新，主要用于配置项改动后，更新缓存， 目前会删除所有config下的缓存，后期再优化成只更新当前修改的配置项 TODO
     * @param: [entity]
     * @return: boolean
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/11/29 15:29
     **/
    @Override
    @CacheEvict(value="config",  allEntries = true)
    public boolean updateById(Config entity) {
        return IConfigService.super.updateById(entity);
    }
}
