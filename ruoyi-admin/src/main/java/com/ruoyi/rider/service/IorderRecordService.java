package com.ruoyi.rider.service;

import java.util.List;
import com.ruoyi.rider.domain.orderRecord;

/**
 * 我要接单Service接口
 * 
 * @author xpl
 * @date 2024-05-17
 */
public interface IorderRecordService 
{
    /**
     * 查询我要接单
     * 
     * @param recordId 我要接单主键
     * @return 我要接单
     */
    public orderRecord selectorderRecordByRecordId(Long recordId);

    /**
     * 查询我要接单列表
     * 
     * @param orderRecord 我要接单
     * @return 我要接单集合
     */
    public List<orderRecord> selectorderRecordList(orderRecord orderRecord);

    /**
     * 新增我要接单
     * 
     * @param orderRecord 我要接单
     * @return 结果
     */
    public int insertorderRecord(orderRecord orderRecord);

    /**
     * 修改我要接单
     * 
     * @param orderRecord 我要接单
     * @return 结果
     */
    public int updateorderRecord(orderRecord orderRecord);

    /**
     * 批量删除我要接单
     * 
     * @param recordIds 需要删除的我要接单主键集合
     * @return 结果
     */
    public int deleteorderRecordByRecordIds(Long[] recordIds);

    /**
     * 删除我要接单信息
     * 
     * @param recordId 我要接单主键
     * @return 结果
     */
    public int deleteorderRecordByRecordId(Long recordId);
}
