package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.PaymentRecord;

import java.util.List;

/**
 * 回款记录表Mapper接口
 * 
 * @author xl
 * @date 2021-04-06
 */
public interface PaymentRecordMapper 
{
    /**
     * 查询回款记录表
     * 
     * @param id 回款记录表ID
     * @return 回款记录表
     */
    public PaymentRecord selectPaymentRecordById(Long id);

    /**
     * 查询回款记录表列表
     * 
     * @param paymentRecord 回款记录表
     * @return 回款记录表集合
     */
    public List<PaymentRecord> selectPaymentRecordList(PaymentRecord paymentRecord);

    /**
     * 新增回款记录表
     * 
     * @param paymentRecord 回款记录表
     * @return 结果
     */
    public int insertPaymentRecord(PaymentRecord paymentRecord);

    /**
     * 修改回款记录表
     * 
     * @param paymentRecord 回款记录表
     * @return 结果
     */
    public int updatePaymentRecord(PaymentRecord paymentRecord);

    /**
     * 删除回款记录表
     * 
     * @param id 回款记录表ID
     * @return 结果
     */
    public int deletePaymentRecordById(Long id);

    /**
     * 批量删除回款记录表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePaymentRecordByIds(Long[] ids);
}
