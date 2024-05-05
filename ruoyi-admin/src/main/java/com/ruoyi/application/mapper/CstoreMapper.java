package com.ruoyi.application.mapper;

import java.util.List;
import com.ruoyi.application.domain.Cstore;

/**
 * 商家申请Mapper接口
 * 
 * @author ruoyi
 * @date 2024-05-05
 */
public interface CstoreMapper 
{
    /**
     * 查询商家申请
     * 
     * @param cstoreId 商家申请主键
     * @return 商家申请
     */
    public Cstore selectCstoreByCstoreId(Long cstoreId);

    /**
     * 查询商家申请列表
     * 
     * @param cstore 商家申请
     * @return 商家申请集合
     */
    public List<Cstore> selectCstoreList(Cstore cstore);

    /**
     * 新增商家申请
     * 
     * @param cstore 商家申请
     * @return 结果
     */
    public int insertCstore(Cstore cstore);

    /**
     * 修改商家申请
     * 
     * @param cstore 商家申请
     * @return 结果
     */
    public int updateCstore(Cstore cstore);

    /**
     * 删除商家申请
     * 
     * @param cstoreId 商家申请主键
     * @return 结果
     */
    public int deleteCstoreByCstoreId(Long cstoreId);

    /**
     * 批量删除商家申请
     * 
     * @param cstoreIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCstoreByCstoreIds(Long[] cstoreIds);
}
