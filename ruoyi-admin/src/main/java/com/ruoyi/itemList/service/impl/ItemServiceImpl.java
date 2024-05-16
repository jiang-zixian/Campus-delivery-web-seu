package com.ruoyi.itemList.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.itemList.domain.CartForm;
import com.ruoyi.record.mapper.RecordMapper;
import com.ruoyi.store.service.ImyStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.itemList.mapper.ItemMapper;
import com.ruoyi.itemList.domain.Item;
import com.ruoyi.itemList.service.IItemService;
import org.springframework.web.bind.annotation.RequestBody;
import com.ruoyi.record.domain.Record;
import com.ruoyi.store.domain.myStore;

/**
 * 商品列表Service业务层处理
 * 
 * @author SYL
 * @date 2024-05-13
 */
@Service
public class ItemServiceImpl implements IItemService 
{
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private ImyStoreService myStoreService;

    /**
     * 查询商品列表
     * 
     * @param iId 商品列表主键
     * @return 商品列表
     */
    @Override
    public Item selectItemByIId(Long iId)
    {
        return itemMapper.selectItemByIId(iId);
    }

    /**
     * 查询商品列表列表
     * 
     * @param item 商品列表
     * @return 商品列表
     */
    @Override
    public List<Item> selectItemList(Item item)
    {
        return itemMapper.selectItemList(item);
    }

    /**
     * 新增商品列表
     * 
     * @param item 商品列表
     * @return 结果
     */
    @Override
    public int insertItem(Item item)
    {
        return itemMapper.insertItem(item);
    }

    /**
     * 修改商品列表
     * 
     * @param item 商品列表
     * @return 结果
     */
    @Override
    public int updateItem(Item item)
    {
        return itemMapper.updateItem(item);
    }

    /**
     * 批量删除商品列表
     * 
     * @param iIds 需要删除的商品列表主键
     * @return 结果
     */
    @Override
    public int deleteItemByIIds(Long[] iIds)
    {
        return itemMapper.deleteItemByIIds(iIds);
    }

    /**
     * 删除商品列表信息
     * 
     * @param iId 商品列表主键
     * @return 结果
     */
    @Override
    public int deleteItemByIId(Long iId)
    {
        return itemMapper.deleteItemByIId(iId);
    }


    /**
     * 检查商品库存是否都满足
     *
     * @param cartForms 商品id和数量
     * @return 结果
     */
    @Override
    public int isStockSufficient(Map<Long,Integer> cartForms) {
        for (Map.Entry<Long, Integer> entry : cartForms.entrySet()) {
            Long itemId = entry.getKey();
            Integer requestedQuantity = entry.getValue();

            Item item = itemMapper.selectItemByIId(itemId);
            if (item == null) {
                return 0;
            }

            if (item.getAmount() < requestedQuantity) {

                return 0;
            }
        }

        //削减对应的库存
        for (Map.Entry<Long, Integer> entry : cartForms.entrySet()) {
            Long itemId = entry.getKey();
            Integer requestedQuantity = entry.getValue();

            Item item = itemMapper.selectItemByIId(itemId);
            item.setAmount(item.getAmount() - requestedQuantity);
            itemMapper.updateItem(item);
        }

        return 1;
    }

    /**
     * 在商店买东西
     *
     * @param record 我的订单
     * @return 结果
     */
    public int buy(Record record){
        if(record.getType()!=0){//在校内商店买东西
            //商店地址自动搜索
            myStore ms=myStoreService.selectmyStoreBySId(record.getsId());
            record.setSrcPosition(ms.getDescription().split(" ")[0]);
        }
        return recordMapper.insertRecord(record);
    }
}
