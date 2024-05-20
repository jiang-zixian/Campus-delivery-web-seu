package com.ruoyi.store.mapper;

import java.util.List;
import com.ruoyi.store.domain.storeRecord;

/**
 * 商店订单记录Mapper接口
 * 
 * @author xpl
 * @date 2024-05-16
 */
public interface storeRecordMapper 
{
    /**
     * 查询商店订单记录
     * 
     * @param recordId 商店订单记录主键
     * @return 商店订单记录
     */
    public storeRecord selectstoreRecordByRecordId(Long recordId);

    /**
     * 查询商店订单记录列表
     * 
     * @param storeRecord 商店订单记录
     * @return 商店订单记录集合
     */
    public List<storeRecord> selectstoreRecordList(storeRecord storeRecord);

    /**
     * 新增商店订单记录
     * 
     * @param storeRecord 商店订单记录
     * @return 结果
     */
    public int insertstoreRecord(storeRecord storeRecord);

    /**
     * 修改商店订单记录
     * 
     * @param storeRecord 商店订单记录
     * @return 结果
     */
    public int updatestoreRecord(storeRecord storeRecord);

    /**
     * 删除商店订单记录
     * 
     * @param recordId 商店订单记录主键
     * @return 结果
     */
    public int deletestoreRecordByRecordId(Long recordId);

    /**
     * 批量删除商店订单记录
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletestoreRecordByRecordIds(Long[] recordIds);
}
