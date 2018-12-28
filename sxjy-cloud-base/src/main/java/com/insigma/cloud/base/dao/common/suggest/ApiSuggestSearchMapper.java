package com.insigma.cloud.base.dao.common.suggest;

import java.util.List;

import com.insigma.mvc.model.SysSuggestKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApiSuggestSearchMapper {
	
	List<SysSuggestKey> searchByKey(SysSuggestKey key);
    
}