package com.ruoyi.common.core.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 回款记录表对象 t_payment_record
 * 
 * @author xl
 * @date 2021-04-06
 */

@Data
public class PaymentRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Excel(name = "id")
    private Long id;

    /** 订单id */
    @Excel(name = "订单id")
    private Long orderId;

    /** 逻辑删除 0：正常 1：删除 */
    @Excel(name = "逻辑删除 0：正常 1：删除")
    private String isDeleted;

    /** 订单code */
    @Excel(name = "订单code")
    private String orderCode;

    /** 序号 */
    @Excel(name = "序号")
    private Integer serialNumber;

    /**  */
    @Excel(name = "")
    private String type;

    /** 图片 */
    @Excel(name = "图片")
    private String pic;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal money;

    /** 回款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

}
