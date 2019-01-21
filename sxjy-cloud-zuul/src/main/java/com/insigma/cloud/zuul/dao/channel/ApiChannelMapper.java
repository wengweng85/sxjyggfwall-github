package com.insigma.cloud.zuul.dao.channel;

import com.insigma.mvc.model.SysApiChannel;
import com.insigma.mvc.model.SysApiInterface;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApiChannelMapper {

    SysApiChannel getApiChannelByAppkey(String appkey);

    List<SysApiInterface> selectByUrl(@Param("url") String url, @Param("appkey") String appkey);
}