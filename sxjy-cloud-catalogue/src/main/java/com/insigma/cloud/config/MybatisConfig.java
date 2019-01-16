package com.insigma.cloud.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created by admin
 */
@Configuration
public class MybatisConfig {
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        //添加配置，也可以指定文件路径
        Properties p = new Properties();
        p.setProperty("helperDialect","oracle");
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");

        //延迟加载启动，默认是false
        p.setProperty("lazyLoadingEnabled","true");
        //积极的懒加载，false的话按需加载，默认是true
        p.setProperty("aggressiveLazyLoading","false");
        //二级缓存
        p.setProperty("cacheEnabled","true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
