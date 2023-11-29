package com.sbvadmin.service;

import com.sbvadmin.model.Config;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.CacheEvict;

/**
 * <p>
 * 配置 服务类
 * </p>
 *
 * @author billy
 * @since 2023-03-02
 */
public interface IConfigService extends IService<Config> {
    /**
     * Notes:  复现更新，主要用于配置项改动后，更新缓存， 目前会删除所有config下的缓存，后期再优化成只更新当前修改的配置项 TODO
     * @param: [entity]
     * @return: boolean
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/11/29 15:29
     **/
    @Override
    @CacheEvict(value="config",  allEntries = true)
    default boolean updateById(Config entity) {
        return IService.super.updateById(entity);
    }
}
