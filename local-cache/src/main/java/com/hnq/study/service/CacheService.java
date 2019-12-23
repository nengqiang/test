package com.hnq.study.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.hnq.study.dao.domain.BaiduHot;
import com.hnq.study.dao.domain.BaiduHotCriteria;
import com.hnq.study.dao.mapper.BaiduHotMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 缓存代码，用到了匿名内部类的方式
 *
 * @author henengqiang
 * @date 2018/11/1
 */
@Service
public class CacheService {

    @Autowired
    private BaiduHotMapper baiduHotMapper;

    private final LoadingCache<String, String> cache;

    /**
     * cache初始化
     */
    public CacheService() {
        this.cache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    /**
                     *  由于数据在本地缓存中保存时间为5s，所以在20s的执行时间中，该方法每隔5秒才会执行一次。
                     * 也就是每隔5s才对数据库进行一次查询操作。
                     */
                    @Override
                    public String load(String id) throws Exception {
                        System.out.println("Method Invoke...");
                        // 这里执行查询数据库，等其他复杂的逻辑
                        return findDrivingThingsById(Integer.valueOf(id));
                    }
                });
    }

    /**
     * 从缓存中获取数据
     */
    public String getStr(String id) throws ExecutionException {
        return cache.get(id);
    }

    /**
     * 从数据库查询数据
     */
    private String findDrivingThingsById(Integer id) {
        BaiduHotCriteria example = new BaiduHotCriteria();
        example.createCriteria().andIdEqualTo(id);
        List<BaiduHot> baiduHots = baiduHotMapper.selectByExampleWithBLOBs(example);
        if (!CollectionUtils.isEmpty(baiduHots)) {
            return baiduHots.get(0).getDrivingThing();
        }
        // 返回 null 的话 CacheLoader会报错
        return "从数据库中没有查到数据";
    }

}
