package com.ruoyi.record.service;

import java.util.List;
import com.ruoyi.record.domain.zitiRecord;
import com.ruoyi.store.domain.mystoreComment;

/**
 * 自提订单Service接口
 * 
 * @author wj
 * @date 2024-05-21
 */
public interface IzitiRecordService 
{
    /**
     * 查询自提订单
     * 
     * @param recordId 自提订单主键
     * @return 自提订单
     */
    public zitiRecord selectzitiRecordByRecordId(Long recordId);

    /**
     * 查询自提订单列表
     * 
     * @param zitiRecord 自提订单
     * @return 自提订单集合
     */
    public List<zitiRecord> selectzitiRecordList(zitiRecord zitiRecord);

    /**
     * 新增自提订单
     * 
     * @param zitiRecord 自提订单
     * @return 结果
     */
    public int insertzitiRecord(zitiRecord zitiRecord);

    /**
     * 修改自提订单
     * 
     * @param zitiRecord 自提订单
     * @return 结果
     */
    public int updatezitiRecord(zitiRecord zitiRecord);

    /**
     * 批量删除自提订单
     * 
     * @param recordIds 需要删除的自提订单主键集合
     * @return 结果
     */
    public int deletezitiRecordByRecordIds(Long[] recordIds);

    /**
     * 删除自提订单信息
     * 
     * @param recordId 自提订单主键
     * @return 结果
     */
    public int deletezitiRecordByRecordId(Long recordId);

    public int insertzitirecordcomment(mystoreComment mystoreComment);
}
