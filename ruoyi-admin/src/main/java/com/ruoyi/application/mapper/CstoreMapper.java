package com.ruoyi.application.mapper;

import java.util.List;
import com.ruoyi.application.domain.Cstore;

/**
 * 商家提交申请Mapper接口
 * 
 * @author xuguowei
 * @date 2024-05-11
 */
public interface CstoreMapper 
{
    /**
     * 查询商家提交申请
     * 
     * @param cstoreId 商家提交申请主键
     * @return 商家提交申请
     */
    public Cstore selectCstoreByCstoreId(Long cstoreId);

    /**
     * 查询商家提交申请列表
     * 
     * @param cstore 商家提交申请
     * @return 商家提交申请集合
     */
    public List<Cstore> selectCstoreList(Cstore cstore);

    /**
     * 新增商家提交申请
     * 
     * @param cstore 商家提交申请
     * @return 结果
     */
    public int insertCstore(Cstore cstore);

    /**
     * 修改商家提交申请
     * 
     * @param cstore 商家提交申请
     * @return 结果
     */
    public int updateCstore(Cstore cstore);

    /**
     * 删除商家提交申请
     * 
     * @param cstoreId 商家提交申请主键
     * @return 结果
     */
    public int deleteCstoreByCstoreId(Long cstoreId);

    /**
     * 批量删除商家提交申请
     * 
     * @param cstoreIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCstoreByCstoreIds(Long[] cstoreIds);

    /**
     * 同意商家申请信息 把status改为1，并授予角色骑商家权限
     *
     * @param cstoreIds 商家申请主键
     * @return 结果
     */
    public int agreeCstoreByCstoreIds(Long[] cstoreIds);

    /**
     * 拒绝商家申请信息 把status改为2
     *
     * @param cstoreIds 商家申请主键
     * @return 结果
     */
    public int refuseCstoreByCstoreIds(Long[] cstoreIds);

    /**
     * 查询申请的uId
     *
     * @param cstoreIds
     * @return uIds
     */
       public Long[] selectUIdsByCstoreIds(Long[] cstoreIds);
}
