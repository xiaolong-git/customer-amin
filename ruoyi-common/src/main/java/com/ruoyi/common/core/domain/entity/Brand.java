package com.ruoyi.common.core.domain.entity;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品品牌表对象 t_brand
 * 
 * @author xl
 * @date 2021-04-06
 */

@Data
public class Brand extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Excel(name = "id")
    private Long id;

    /** 逻辑删除 0：正常 1：删除 */
    @Excel(name = "逻辑删除 0：正常 1：删除")
    private String isDeleted;

    /** 品牌编号 */
    @Excel(name = "品牌编号")
    private String code;

    /** 品牌名称 */
    @Excel(name = "品牌名称")
    private String name;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 品牌类型：0：商品品牌 */
    @Excel(name = "品牌类型：0：商品品牌")
    private String type;

    /** 图片 */
    @Excel(name = "图片")
    private String pic;

}
