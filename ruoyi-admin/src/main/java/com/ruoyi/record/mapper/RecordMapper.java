package com.ruoyi.record.mapper;

import java.util.List;
import com.ruoyi.record.domain.Record;
import com.ruoyi.store.domain.mystoreComment;

/**
 * 我的订单Mapper接口
 * 
 * @author ruoyi
 * @date 2024-05-11
 */
public interface RecordMapper 
{
    /**
     * 查询我的订单
     * 
     * @param recordId 我的订单主键
     * @return 我的订单
     */
    public Record selectRecordByRecordId(Long recordId);

    /**
     * 查询我的订单列表
     * 
     * @param record 我的订单
     * @return 我的订单集合
     */
    public List<Record> selectRecordList(Record record);

    /**
     * 新增我的订单
     * 
     * @param record 我的订单
     * @return 结果
     */
    public int insertRecord(Record record);

    /**
     * 修改我的订单
     * 
     * @param record 我的订单
     * @return 结果
     */
    public int updateRecord(Record record);

    /**
     * 删除我的订单
     * 
     * @param recordId 我的订单主键
     * @return 结果
     */
    public int deleteRecordByRecordId(Long recordId);

    /**
     * 批量删除我的订单
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRecordByRecordIds(Long[] recordIds);

    public int insertrecordcomment(mystoreComment mystoreComment);
}
