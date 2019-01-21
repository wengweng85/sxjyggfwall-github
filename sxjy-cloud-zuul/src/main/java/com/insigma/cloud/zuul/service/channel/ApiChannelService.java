package com.insigma.cloud.zuul.service.channel;

import org.apache.ibatis.annotations.Param;

public interface ApiChannelService {

    boolean isAppkeyValid(String appkey);

    boolean isUrlValid(@Param("url") String url, @Param("appkey") String appkey);
}
