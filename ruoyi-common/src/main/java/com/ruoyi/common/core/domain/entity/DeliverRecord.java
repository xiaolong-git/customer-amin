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
 * 发货记录表对象 t_deliver_record
 * 
 * @author xl
 * @date 2021-07-15
 */

@Data
public class DeliverRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    @Excel(name = "")
    private String type;

    /** 回款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "回款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryTime;

    /** 逻辑删除 0：正常 1：删除 */
    @Excel(name = "逻辑删除 0：正常 1：删除")
    private String isDeleted;

    /** 发货数量 */
    @Excel(name = "发货数量")
    private BigDecimal deliveryQuantity;

    /** 单位 */
    @Excel(name = "单位")
    private String deliveryUnit;

    /** id */
    @Excel(name = "id")
    private Long id;

    /** 运费 */
    @Excel(name = "运费")
    private BigDecimal deliveryFreight;

    /** 订单id */
    @Excel(name = "订单id")
    private Long orderId;

    /** 商品id */
    @Excel(name = "品牌id")
    private Long brandId;

    /** 商品id */
    @Excel(name = "商品id")
    private Long productId;

    /** 订单code */
    @Excel(name = "订单code")
    private String orderCode;

}
