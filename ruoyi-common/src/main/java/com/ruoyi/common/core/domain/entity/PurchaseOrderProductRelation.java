package com.ruoyi.common.core.domain.entity;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 采购订单商品关联表对象 t_purchase_order_product_relation
 * 
 * @author xl
 * @date 2021-04-06
 */
@Data
public class PurchaseOrderProductRelation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Excel(name = "id")
    private Long id;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal unitPrice;

    /** 订单id */
    @Excel(name = "订单id")
    private Long orderId;

    /** 订单code */
    @Excel(name = "订单code")
    private String orderCode;

    /** 商品id */
    @Excel(name = "品牌id")
    private Long brandId;

    /** 商品id */
    @Excel(name = "商品id")
    private Long productId;

    /** 商品code */
    @Excel(name = "商品code")
    private String productCode;

    /** 吨位 */
    @Excel(name = "吨位")
    private String tonnage;

    /** 数量 */
    @Excel(name = "数量")
    private BigDecimal quantity;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 运费 */
    @Excel(name = "运费")
    private BigDecimal freight;

    /** 逻辑删除 0：正常 1：删除 */
    @Excel(name = "逻辑删除 0：正常 1：删除")
    private String isDeleted;


}
