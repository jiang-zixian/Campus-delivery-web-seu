package com.ruoyi.record.mapper;

import java.util.List;
import com.ruoyi.record.domain.zitiRecord;

/**
 * 自提订单Mapper接口
 * 
 * @author wj
 * @date 2024-05-21
 */
public interface zitiRecordMapper 
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
     * 删除自提订单
     * 
     * @param recordId 自提订单主键
     * @return 结果
     */
    public int deletezitiRecordByRecordId(Long recordId);

    /**
     * 批量删除自提订单
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletezitiRecordByRecordIds(Long[] recordIds);
}
