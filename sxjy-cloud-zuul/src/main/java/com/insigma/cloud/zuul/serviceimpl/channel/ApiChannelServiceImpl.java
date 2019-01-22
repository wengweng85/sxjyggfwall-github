package com.insigma.cloud.zuul.serviceimpl.channel;

import com.insigma.cloud.zuul.dao.channel.ApiChannelMapper;
import com.insigma.cloud.zuul.service.channel.ApiChannelService;
import com.insigma.mvc.model.SysApiChannel;
import com.insigma.mvc.model.SysApiInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wengsh on 2019/1/21.
 */
@Service
public class ApiChannelServiceImpl implements ApiChannelService {

    @Autowired
    private ApiChannelMapper apiChannelMapper;

    @Override
    public boolean isAppkeyValid(String appkey) {
        SysApiChannel sysApiChannel=apiChannelMapper.getApiChannelByAppkey(appkey);
        if(sysApiChannel!=null){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public List<SysApiChannel> getAllApiChannel() {
        return apiChannelMapper.getAllApiChannel();
    }

    @Override
    public boolean isUrlValid(String url, String appkey) {
        List<SysApiInterface> list = apiChannelMapper.selectByUrl(url, appkey);
        if (list.size()>0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<SysApiInterface> selectAllUrlByAppkey(String appkey) {
        return apiChannelMapper.selectAllUrlByAppkey(appkey);
    }
}
