package com.ruoyi.common.core.domain.entity;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采购订单合同关联表对象 t_purchase_order_contract_relation
 * 
 * @author xl
 * @date 2021-04-06
 */
@Data
public class PurchaseOrderContractRelation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Excel(name = "id")
    private Long id;

    /** 逻辑删除 0：正常 1：删除 */
    @Excel(name = "逻辑删除 0：正常 1：删除")
    private String isDeleted;

    /** 订单id */
    @Excel(name = "订单id")
    private Long orderId;

    /** 订单code */
    @Excel(name = "订单code")
    private String orderCode;

    /** 序号 */
    @Excel(name = "序号")
    private Integer serialNumber;

    /** 合同类型：0：默认普通合同 */
    @Excel(name = "合同类型：0：默认普通合同")
    private String type;

    /** 合同图片 */
    @Excel(name = "合同图片")
    private String pic;

}
