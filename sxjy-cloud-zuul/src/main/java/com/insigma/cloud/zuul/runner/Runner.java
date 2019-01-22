package com.insigma.cloud.zuul.runner;

import com.insigma.cloud.common.guava.CacheMap;
import com.insigma.cloud.zuul.service.channel.ApiChannelService;
import com.insigma.mvc.model.SysApiChannel;
import com.insigma.mvc.model.SysApiInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wengsh on 2019/1/22.
 * 项目初始化
 */
@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private ApiChannelService apiChannelService;

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
