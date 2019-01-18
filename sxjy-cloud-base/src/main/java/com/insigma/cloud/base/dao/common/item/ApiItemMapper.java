package com.insigma.cloud.base.dao.common.item;

import com.insigma.mvc.model.Item;
import com.insigma.mvc.model.ItemMaterial;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author  admin
 */
@Mapper
public interface ApiItemMapper  {

    public List<Item> getItemList();

    public Item getItemById(String item_id);

    public List<ItemMaterial> getItemMeterial(String item_id);
    
}
