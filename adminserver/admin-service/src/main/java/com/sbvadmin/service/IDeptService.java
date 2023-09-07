package com.sbvadmin.service;

import com.sbvadmin.model.Dept;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * <p>
 * 机构管理 服务类
 * </p>
 *
 * @author billy
 * @since 2023-03-02
 */
@CacheConfig(cacheNames = "dept")
public interface IDeptService extends IService<Dept> {

    /*
     * Notes:  以树形结构返回机构数据
     * @param: []
     * @return: java.util.List<com.sbvadmin.model.Dept>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/3/2 21:16
     **/
    public List<Dept> getAllDepts();


    /**
     * Notes:  获取用户所属的所有机构
     * @param: [id]
     * @return: java.util.List<com.sbvadmin.model.Dept>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/9/7 17:49
     **/
    @Cacheable(key = "#root.methodName +'_'+ #root.args")
    public List<Dept> getDeptsByUserId(Long id);
}
