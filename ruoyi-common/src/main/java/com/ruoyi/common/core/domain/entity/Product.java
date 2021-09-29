package com.ruoyi.common.core.domain.entity;

import java.math.BigDecimal;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品表对象 t_product
 * 
 * @author ${author}
 * @date 2021-04-06
 */

@Data
public class Product extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Excel(name = "id")
    private Long id;

    /** 逻辑删除 0：正常 1：删除 */
    @Excel(name = "逻辑删除 0：正常 1：删除")
    private String isDeleted;

    /** 图片 */
    @Excel(name = "图片")
    private String pic;

    /** 品牌id */
    @Excel(name = "品牌id")
    private Long brandId;

    /** 指导价 */
    @Excel(name = "指导价")
    private BigDecimal guidePrice;

    /** 品牌code */
    @Excel(name = "品牌code")
    private String brandCode;

    /** 品牌名称 */
    @Excel(name = "品牌名称")
    private String brandName;

    /** 销售价 */
    @Excel(name = "销售价")
    private BigDecimal sellPrice;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private String code;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String name;

    /** 商品类型 */
    @Excel(name = "商品类型")
    private String type;

}
