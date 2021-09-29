package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.DeliverRecord;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.mapper.DeliverRecordMapper;
import com.ruoyi.system.service.IDeliverRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 发货记录表Service业务层处理
 * 
 * @author xl
 * @date 2021-07-15
 */
@Service
public class DeliverRecordServiceImpl implements IDeliverRecordService
{
    @Autowired
    private DeliverRecordMapper deliverRecordMapper;

    /**
     * 查询发货记录表
     * 
     * @param id 发货记录表ID
     * @return 发货记录表
     */
    @Override
    public DeliverRecord selectDeliverRecordById(Long id)
    {
        return deliverRecordMapper.selectDeliverRecordById(id);
    }

    /**
     * 查询发货记录表列表
     * 
     * @param deliverRecord 发货记录表
     * @return 发货记录表
     */
    @Override
    public List<DeliverRecord> selectDeliverRecordList(DeliverRecord deliverRecord)
    {
        return deliverRecordMapper.selectDeliverRecordList(deliverRecord);
    }

    /**
     * 新增发货记录表
     * 
     * @param deliverRecord 发货记录表
     * @return 结果
     */
    @Override
    public int insertDeliverRecord(DeliverRecord deliverRecord)
    {
        deliverRecord.setCreateTime(DateUtils.getNowDate());
        return deliverRecordMapper.insertDeliverRecord(deliverRecord);
    }

    /**
     * 修改发货记录表
     * 
     * @param deliverRecord 发货记录表
     * @return 结果
     */
    @Override
    public int updateDeliverRecord(DeliverRecord deliverRecord)
    {
        deliverRecord.setUpdateTime(DateUtils.getNowDate());
        return deliverRecordMapper.updateDeliverRecord(deliverRecord);
    }

    /**
     * 批量删除发货记录表
     * 
     * @param ids 需要删除的发货记录表ID
     * @return 结果
     */
    @Override
    public int deleteDeliverRecordByIds(Long[] ids)
    {
        return deliverRecordMapper.deleteDeliverRecordByIds(ids);
    }

    /**
     * 删除发货记录表信息
     * 
     * @param id 发货记录表ID
     * @return 结果
     */
    @Override
    public int deleteDeliverRecordById(Long id)
    {
        return deliverRecordMapper.deleteDeliverRecordById(id);
    }
}
