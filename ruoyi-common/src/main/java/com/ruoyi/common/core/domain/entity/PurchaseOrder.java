package com.ruoyi.common.core.domain.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购订单表 t_purchase_order
 * 
 * @author ruoyi
 */

@Data
public class PurchaseOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Excel(name = "id")
    private Long id;

    /** 订单类型:0：默认普通订单 */
    @Excel(name = "订单类型:0：默认普通订单")
    private String type;

    /** 订单状态 0：已保存: 1：审批中 2：审批通过 3：审批拒绝 4：协同审批 */
    @Excel(name = "订单状态 0：已保存: 1：审批中 2：审批通过 3：审批拒绝 4：协同审批")
    private String status;

    /** 状态备注 */
    @Excel(name = "状态备注")
    private String statusRemark;

    /** 品牌名称 */
    @Excel(name = "品牌名称")
    private String brandName;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String code;

    /** 开票类型 专票:INVOICE_SPECIAL 普票:INVOICE_COMMON */
    @Excel(name = "开票类型 专票:INVOICE_SPECIAL 普票:INVOICE_COMMON")
    private String invoiceType;

    /** 产品id */
    @Excel(name = "产品id")
    private String productId;

    /** 吨位 */
    @Excel(name = "吨位")
    private String tonnage;

    /** 提货方式 1:直发 2：自提 */
    @Excel(name = "提货方式 1:直发 2：自提")
    private String pickType;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal unitPrice;

    /** 合同方 1:买方 2：卖方 */
    @Excel(name = "合同方 1:买方 2：卖方")
    private String contractOwnership;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 客户id */
    @Excel(name = "客户id")
    private String customerId;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal money;

    /** 付款方式 : 先款:PAY_FIRST  票到:TICKET_ARRIVE 货到:GOODS_ARRIVE */
    @Excel(name = "付款方式 : 先款:PAY_FIRST  票到:TICKET_ARRIVE 货到:GOODS_ARRIVE")
    private String payType;

    /** 付款备注 */
    @Excel(name = "付款备注")
    private String payRemark;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String customerName;

    /** 逻辑删除 0：正常 1：删除 */
    @Excel(name = "逻辑删除 0：正常 1：删除")
    private String isDeleted;

    /** 运费 */
    @Excel(name = "运费")
    private BigDecimal freight;

    /** 是否开票 0：否 1：是 */
    @Excel(name = "是否开票 0：否 1：是")
    private String makeInvoice;

    /** 品牌id */
    @Excel(name = "品牌id")
    private String brandId;

}
