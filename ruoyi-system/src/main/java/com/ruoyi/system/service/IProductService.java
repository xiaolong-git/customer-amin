package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.Product;

import java.util.List;

/**
 * 商品表Service接口
 * 
 * @author ${author}
 * @date 2021-04-06
 */
public interface IProductService 
{
    /**
     * 查询商品表
     * 
     * @param id 商品表ID
     * @return 商品表
     */
    public Product selectProductById(Long id);

    /**
     * 查询商品表列表
     * 
     * @param product 商品表
     * @return 商品表集合
     */
    public List<Product> selectProductList(Product product);

    /**
     * 新增商品表
     * 
     * @param product 商品表
     * @return 结果
     */
    public int insertProduct(Product product);

    /**
     * 修改商品表
     * 
     * @param product 商品表
     * @return 结果
     */
    public int updateProduct(Product product);

    /**
     * 批量删除商品表
     * 
     * @param ids 需要删除的商品表ID
     * @return 结果
     */
    public int deleteProductByIds(Long[] ids);

    /**
     * 删除商品表信息
     * 
     * @param id 商品表ID
     * @return 结果
     */
    public int deleteProductById(Long id);
}
