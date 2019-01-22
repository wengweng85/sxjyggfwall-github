package com.insigma.cloud.zuul.service.channel;

import com.insigma.mvc.model.SysApiChannel;
import com.insigma.mvc.model.SysApiInterface;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApiChannelService {

    boolean isAppkeyValid(String appkey);

    List<SysApiChannel> getAllApiChannel();

    boolean isUrlValid(@Param("url") String url, @Param("appkey") String appkey);

    List<SysApiInterface> selectAllUrlByAppkey(String appkey);
}
