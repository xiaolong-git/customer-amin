package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.PaymentRecord;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.mapper.PaymentRecordMapper;
import com.ruoyi.system.service.IPaymentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 回款记录表Service业务层处理
 * 
 * @author xl
 * @date 2021-04-06
 */
@Service
public class PaymentRecordServiceImpl implements IPaymentRecordService
{
    @Autowired
    private PaymentRecordMapper paymentRecordMapper;

    /**
     * 查询回款记录表
     * 
     * @param id 回款记录表ID
     * @return 回款记录表
     */
    @Override
    public PaymentRecord selectPaymentRecordById(Long id)
    {
        return paymentRecordMapper.selectPaymentRecordById(id);
    }

    /**
     * 查询回款记录表列表
     * 
     * @param paymentRecord 回款记录表
     * @return 回款记录表
     */
    @Override
    public List<PaymentRecord> selectPaymentRecordList(PaymentRecord paymentRecord)
    {
        return paymentRecordMapper.selectPaymentRecordList(paymentRecord);
    }

    /**
     * 新增回款记录表
     * 
     * @param paymentRecord 回款记录表
     * @return 结果
     */
    @Override
    public int insertPaymentRecord(PaymentRecord paymentRecord)
    {
        paymentRecord.setCreateTime(DateUtils.getNowDate());
        return paymentRecordMapper.insertPaymentRecord(paymentRecord);
    }

    /**
     * 修改回款记录表
     * 
     * @param paymentRecord 回款记录表
     * @return 结果
     */
    @Override
    public int updatePaymentRecord(PaymentRecord paymentRecord)
    {
        paymentRecord.setUpdateTime(DateUtils.getNowDate());
        return paymentRecordMapper.updatePaymentRecord(paymentRecord);
    }

    /**
     * 批量删除回款记录表
     * 
     * @param ids 需要删除的回款记录表ID
     * @return 结果
     */
    @Override
    public int deletePaymentRecordByIds(Long[] ids)
    {
        return paymentRecordMapper.deletePaymentRecordByIds(ids);
    }

    /**
     * 删除回款记录表信息
     * 
     * @param id 回款记录表ID
     * @return 结果
     */
    @Override
    public int deletePaymentRecordById(Long id)
    {
        return paymentRecordMapper.deletePaymentRecordById(id);
    }
}
