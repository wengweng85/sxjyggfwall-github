package com.insigma.cloud.zuul.runner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.insigma.cloud.common.guava.CacheMap;
import com.insigma.cloud.common.utils.SerializeUtil;
import com.insigma.cloud.zuul.filter.RateLimitFilter;
import com.insigma.cloud.zuul.service.channel.ApiChannelService;
import com.insigma.mvc.model.SysApiChannel;
import com.insigma.mvc.model.SysApiInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wengsh on 2019/1/22.
 * 项目初始化
 */
@Component
public class Runner implements CommandLineRunner {

    private final static Logger logger = LoggerFactory.getLogger(Runner.class);

    @Autowired
    private ApiChannelService apiChannelService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("...init resources by implements CommandLineRunner....");
        List<SysApiChannel> list=apiChannelService.getAllApiChannel();
        for(int i=0;i<list.size();i++){
            SysApiChannel tempChannel=list.get(i);
            String appkey=tempChannel.getChannelAppkey();
            List<SysApiInterface>  sysApiInterfaceList=apiChannelService.selectAllUrlByAppkey(appkey);
            tempChannel.setUrls(sysApiInterfaceList);
            CacheMap.put(appkey,tempChannel);
        }
    }
}
