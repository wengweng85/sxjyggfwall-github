package com.insigma.cloud.base.serviceimpl.common.suggest;

import com.github.pagehelper.PageHelper;
import com.insigma.cloud.base.dao.common.suggest.ApiSuggestSearchMapper;
import com.insigma.cloud.base.service.common.suggest.ApiSuggestSearchService;
import com.insigma.mvc.model.SysSuggestKey;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author admin
 *
 */
@Service
public class ApiSuggestSearchServiceImpl  implements ApiSuggestSearchService {

	@Resource
	private ApiSuggestSearchMapper suggestSearchMapper;

	@Override
	public List<SysSuggestKey> searchByKey(SysSuggestKey key) {
		PageHelper.offsetPage(0, 10);
		String regexStr = "[\u4E00-\u9FA5]+";
		//判断是否是中文
		if(key.getKeyword().matches(regexStr)){
			key.setName(key.getKeyword());
		}else{
			key.setKey(key.getKeyword());
		}
		return suggestSearchMapper.searchByKey(key);
	}
	
}