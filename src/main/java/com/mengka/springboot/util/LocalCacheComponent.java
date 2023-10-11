package com.mengka.springboot.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.TimeUnit;

/**
 * 缓存工具类
 *
 * @author huangyy
 * @version cabbage-forward2.0,2018-2-27
 * @since cabbage-forward2.0
 */
@Log4j2
public class LocalCacheComponent {

    private final static Cache<Object, Object> cache = CacheBuilder.newBuilder()
            //设置cache的初始大小为10，要合理设置该值
            .initialCapacity(300)
            //设置并发数为20，即同一时间最多只能有20个线程往cache执行写入操作
            .concurrencyLevel(20)
            //设置cache中的数据在写入之后的存活时间为30days
            .expireAfterWrite(10, TimeUnit.MINUTES)
            //构建cache实例
            .build();

    public void put(Object key, Object value) {
        cache.put(key, value);
    }

    public Object get(Object key) {
        return cache.getIfPresent(key);
    }

    public void delete(Object key) {
        cache.invalidate(key);
    }

    public static LocalCacheComponent getInstance() {
        return LocalCacheComponentHolder.forwardExecuter_holder;
    }

    private static class LocalCacheComponentHolder {
        private static LocalCacheComponent forwardExecuter_holder = new LocalCacheComponent();
    }
}
