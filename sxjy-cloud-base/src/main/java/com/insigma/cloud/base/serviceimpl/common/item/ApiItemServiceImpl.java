package com.insigma.cloud.base.serviceimpl.common.item;

import com.insigma.cloud.base.dao.common.item.ApiItemMapper;
import com.insigma.cloud.base.service.common.item.ApiItemService;
import com.insigma.mvc.model.Item;
import com.insigma.mvc.model.ItemMaterial;
import com.insigma.mvc.model.ItemType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author admin
 */
@Service
@Transactional
public class ApiItemServiceImpl implements ApiItemService {

    @Resource
    private ApiItemMapper apiItemMapper;

    @Override
    public List<Item> getItemList() {
        return apiItemMapper.getItemList();
    }

    @Override
    public Item getItemById(ItemType itemtype) {
    	return  apiItemMapper.getItemById(itemtype.getCode());
    }

	@Override
	public List<ItemMaterial> getItemMaterialById(String item_id) {
    	List<ItemMaterial> itemMaterialList=apiItemMapper.getItemMeterial(item_id);
    	return itemMaterialList;
	}

	@Override
	public Item getItemById(String id) {
		return  apiItemMapper.getItemById(id);
	}
}