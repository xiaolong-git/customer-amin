package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.DeliverRecord;

import java.util.List;

/**
 * 发货记录表Mapper接口
 * 
 * @author xl
 * @date 2021-07-15
 */
public interface DeliverRecordMapper 
{
    /**
     * 查询发货记录表
     * 
     * @param id 发货记录表ID
     * @return 发货记录表
     */
    public DeliverRecord selectDeliverRecordById(Long id);

    /**
     * 查询发货记录表列表
     * 
     * @param deliverRecord 发货记录表
     * @return 发货记录表集合
     */
    public List<DeliverRecord> selectDeliverRecordList(DeliverRecord deliverRecord);

    /**
     * 新增发货记录表
     * 
     * @param deliverRecord 发货记录表
     * @return 结果
     */
    public int insertDeliverRecord(DeliverRecord deliverRecord);

    /**
     * 修改发货记录表
     * 
     * @param deliverRecord 发货记录表
     * @return 结果
     */
    public int updateDeliverRecord(DeliverRecord deliverRecord);

    /**
     * 删除发货记录表
     * 
     * @param id 发货记录表ID
     * @return 结果
     */
    public int deleteDeliverRecordById(Long id);

    /**
     * 批量删除发货记录表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDeliverRecordByIds(Long[] ids);
}
