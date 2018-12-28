package com.insigma.cloud.base.service.common.item;

import com.insigma.mvc.model.Item;
import com.insigma.mvc.model.ItemMaterial;
import com.insigma.mvc.model.ItemType;

import java.util.List;


/**
 * Ö÷Ò³service
 *
 * @author admin
 */
public interface ApiItemService {

    public List<Item> getItemList();

    public Item getItemById(ItemType itemtype);
    
    public Item getItemById(String id);
    
    public List<ItemMaterial> getItemMaterialById(String item_id);
}
