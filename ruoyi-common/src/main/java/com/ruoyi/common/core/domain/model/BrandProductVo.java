package com.ruoyi.common.core.domain.model;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 添加商品实体
 */
@Data
public class BrandProductVo {

    /** 品牌id */
    private Long brandId;

    /** 商品id */
    private Long productId;

    /** 单价 */
    private BigDecimal unitPrice;

    /** 吨位 */
    private String tonnage;

    /** 运费 */
    private BigDecimal freight;

    /** 数量 */
    private BigDecimal quantity;

    /** 单位 */
    private String unit;

}
