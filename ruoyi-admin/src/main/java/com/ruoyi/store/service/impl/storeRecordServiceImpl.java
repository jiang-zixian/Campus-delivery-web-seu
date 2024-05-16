package com.ruoyi.store.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.store.mapper.storeRecordMapper;
import com.ruoyi.store.domain.storeRecord;
import com.ruoyi.store.service.IstoreRecordService;

/**
 * 商店订单记录Service业务层处理
 * 
 * @author xpl
 * @date 2024-05-16
 */
@Service
public class storeRecordServiceImpl implements IstoreRecordService 
{
    @Autowired
    private storeRecordMapper storeRecordMapper;

    /**
     * 查询商店订单记录
     * 
     * @param recordId 商店订单记录主键
     * @return 商店订单记录
     */
    @Override
    public storeRecord selectstoreRecordByRecordId(Long recordId)
    {
        return storeRecordMapper.selectstoreRecordByRecordId(recordId);
    }

    /**
     * 查询商店订单记录列表
     * 
     * @param storeRecord 商店订单记录
     * @return 商店订单记录
     */
    @Override
    public List<storeRecord> selectstoreRecordList(storeRecord storeRecord)
    {
        return storeRecordMapper.selectstoreRecordList(storeRecord);
    }

    /**
     * 新增商店订单记录
     * 
     * @param storeRecord 商店订单记录
     * @return 结果
     */
    @Override
    public int insertstoreRecord(storeRecord storeRecord)
    {
        return storeRecordMapper.insertstoreRecord(storeRecord);
    }

    /**
     * 修改商店订单记录
     * 
     * @param storeRecord 商店订单记录
     * @return 结果
     */
    @Override
    public int updatestoreRecord(storeRecord storeRecord)
    {
        return storeRecordMapper.updatestoreRecord(storeRecord);
    }

    /**
     * 批量删除商店订单记录
     * 
     * @param recordIds 需要删除的商店订单记录主键
     * @return 结果
     */
    @Override
    public int deletestoreRecordByRecordIds(Long[] recordIds)
    {
        return storeRecordMapper.deletestoreRecordByRecordIds(recordIds);
    }

    /**
     * 删除商店订单记录信息
     * 
     * @param recordId 商店订单记录主键
     * @return 结果
     */
    @Override
    public int deletestoreRecordByRecordId(Long recordId)
    {
        return storeRecordMapper.deletestoreRecordByRecordId(recordId);
    }
}
