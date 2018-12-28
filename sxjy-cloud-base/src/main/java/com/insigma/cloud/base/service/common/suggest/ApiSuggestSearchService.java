package com.insigma.cloud.base.service.common.suggest;

import java.util.List;

import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SysSuggestKey;


/**

 * @author admin
 *
 */
public interface ApiSuggestSearchService {
	
	List<SysSuggestKey> searchByKey(SysSuggestKey key);
	
}
