package com.ruoyi.rider.service;

import java.util.List;
import com.ruoyi.rider.domain.riderRecord;

/**
 * 历史接单记录Service接口
 * 
 * @author xpl
 * @date 2024-05-22
 */
public interface IriderRecordService 
{
    /**
     * 查询历史接单记录
     * 
     * @param recordId 历史接单记录主键
     * @return 历史接单记录
     */
    public riderRecord selectriderRecordByRecordId(Long recordId);

    /**
     * 查询历史接单记录列表
     * 
     * @param riderRecord 历史接单记录
     * @return 历史接单记录集合
     */
    public List<riderRecord> selectriderRecordList(riderRecord riderRecord);

    /**
     * 新增历史接单记录
     * 
     * @param riderRecord 历史接单记录
     * @return 结果
     */
    public int insertriderRecord(riderRecord riderRecord);

    /**
     * 修改历史接单记录
     * 
     * @param riderRecord 历史接单记录
     * @return 结果
     */
    public int updateriderRecord(riderRecord riderRecord);

    /**
     * 批量删除历史接单记录
     * 
     * @param recordIds 需要删除的历史接单记录主键集合
     * @return 结果
     */
    public int deleteriderRecordByRecordIds(Long[] recordIds);

    /**
     * 删除历史接单记录信息
     * 
     * @param recordId 历史接单记录主键
     * @return 结果
     */
    public int deleteriderRecordByRecordId(Long recordId);
}
