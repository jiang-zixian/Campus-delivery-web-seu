package com.ruoyi.rider.mapper;

import java.util.List;
import com.ruoyi.rider.domain.orderRecord;

/**
 * 我要接单Mapper接口
 * 
 * @author xpl
 * @date 2024-05-17
 */
public interface orderRecordMapper 
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
     * 查询当前骑手是否有未完成的接单
     *
     * @param uId 当前骑手的用户Id
     * @return 当前接单的recordId，如无当前接单，则返回null
     */
    public Long ifHaveOrder(Long uId);

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
     * 删除我要接单
     * 
     * @param recordId 我要接单主键
     * @return 结果
     */
    public int deleteorderRecordByRecordId(Long recordId);

    /**
     * 批量删除我要接单
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteorderRecordByRecordIds(Long[] recordIds);
}
