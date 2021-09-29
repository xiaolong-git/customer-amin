package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.Brand;

import java.util.List;

/**
 * 商品品牌表Mapper接口
 * 
 * @author xl
 * @date 2021-04-06
 */
public interface BrandMapper 
{
    /**
     * 查询商品品牌表
     * 
     * @param id 商品品牌表ID
     * @return 商品品牌表
     */
    public Brand selectBrandById(Long id);

    /**
     * 查询商品品牌表列表
     * 
     * @param brand 商品品牌表
     * @return 商品品牌表集合
     */
    public List<Brand> selectBrandList(Brand brand);

    /**
     * 新增商品品牌表
     * 
     * @param brand 商品品牌表
     * @return 结果
     */
    public int insertBrand(Brand brand);

    /**
     * 修改商品品牌表
     * 
     * @param brand 商品品牌表
     * @return 结果
     */
    public int updateBrand(Brand brand);

    /**
     * 删除商品品牌表
     * 
     * @param id 商品品牌表ID
     * @return 结果
     */
    public int deleteBrandById(Long id);

    /**
     * 批量删除商品品牌表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBrandByIds(Long[] ids);
}
