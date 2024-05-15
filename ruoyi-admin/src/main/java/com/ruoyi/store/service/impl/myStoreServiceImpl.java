package com.ruoyi.store.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.store.mapper.myStoreMapper;
import com.ruoyi.store.domain.myStore;
import com.ruoyi.store.service.ImyStoreService;

/**
 * 我的商店Service业务层处理
 * 
 * @author xpl
 * @date 2024-05-13
 */
@Service
public class myStoreServiceImpl implements ImyStoreService 
{
    @Autowired
    private myStoreMapper myStoreMapper;

    /**
     * 查询我的商店
     * 
     * @param sId 我的商店主键
     * @return 我的商店
     */
    @Override
    public myStore selectmyStoreBySId(Long sId)
    {
        return myStoreMapper.selectmyStoreBySId(sId);
    }

    /**
     * 查询我的商店列表
     * 
     * @param myStore 我的商店
     * @return 我的商店
     */
    @Override
    public List<myStore> selectmyStoreList(myStore myStore)
    {
        return myStoreMapper.selectmyStoreList(myStore);
    }

    /**
     * 新增我的商店
     * 
     * @param myStore 我的商店
     * @return 结果
     */
    @Override
    public int insertmyStore(myStore myStore)
    {
        return myStoreMapper.insertmyStore(myStore);
    }

    /**
     * 修改我的商店
     * 
     * @param myStore 我的商店
     * @return 结果
     */
    @Override
    public int updatemyStore(myStore myStore)
    {
        return myStoreMapper.updatemyStore(myStore);
    }

    /**
     * 批量删除我的商店
     * 
     * @param sIds 需要删除的我的商店主键
     * @return 结果
     */
    @Override
    public int deletemyStoreBySIds(Long[] sIds)
    {
        return myStoreMapper.deletemyStoreBySIds(sIds);
    }

    /**
     * 删除我的商店信息
     * 
     * @param sId 我的商店主键
     * @return 结果
     */
    @Override
    public int deletemyStoreBySId(Long sId)
    {
        return myStoreMapper.deletemyStoreBySId(sId);
    }
}
