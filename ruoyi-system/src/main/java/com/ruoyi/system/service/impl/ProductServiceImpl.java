package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.Brand;
import com.ruoyi.common.core.domain.entity.Product;
import com.ruoyi.common.core.domain.entity.PurchaseOrderProductRelation;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.mapper.ProductMapper;
import com.ruoyi.system.mapper.PurchaseOrderProductRelationMapper;
import com.ruoyi.system.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 商品表Service业务层处理
 * 
 * @author ${author}
 * @date 2021-04-06
 */
@Service
public class ProductServiceImpl implements IProductService
{
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private PurchaseOrderProductRelationMapper purchaseOrderProductRelationMapper;

    /**
     * 查询商品表
     * 
     * @param id 商品表ID
     * @return 商品表
     */
    @Override
    public Product selectProductById(Long id)
    {
        return productMapper.selectProductById(id);
    }

    /**
     * 查询商品表列表
     * 
     * @param product 商品表
     * @return 商品表
     */
    @Override
    public List<Product> selectProductList(Product product)
    {
        return productMapper.selectProductList(product);
    }

    /**
     * 新增商品表
     * 
     * @param product 商品表
     * @return 结果
     */
    @Override
    public int insertProduct(Product product)
    {
        if(product.getBrandId() == null){
            throw new CustomException("请选择品牌");
        }
        if(StringUtils.isEmpty(product.getName())){
            throw new CustomException("请输入商品名称");
        }

        //查询是否有重复名称的品牌
        Product queryParam = new Product();
        queryParam.setName(product.getName());
        queryParam.setBrandId(product.getBrandId());
        if(!CollectionUtils.isEmpty(productMapper.selectProductList(queryParam))){
            throw new CustomException("该商品已存在，请检验后填写");
        }

        product.setCreateBy(SecurityUtils.getUsername());
        product.setCreateTime(DateUtils.getNowDate());
        productMapper.insertProduct(product);
        String id = product.getId().toString();
        product.setCode(StringUtils.getTableCode("PRODUCT-", id));
        return productMapper.updateProduct(product);
    }

    /**
     * 修改商品表
     * 
     * @param product 商品表
     * @return 结果
     */
    @Override
    public int updateProduct(Product product)
    {
        if(product.getBrandId() == null){
            throw new CustomException("请选择品牌");
        }
        if(StringUtils.isEmpty(product.getName())){
            throw new CustomException("请输入商品名称");
        }
        //查询是否有重复名称的品牌
        Product queryParam = new Product();
        queryParam.setName(product.getName());
        queryParam.setBrandId(product.getBrandId());
        List<Product> productList = productMapper.selectProductList(queryParam);
        if(!CollectionUtils.isEmpty(productList)){
            if(!productList.get(0).getId().equals(product.getId())){
                throw new CustomException("该商品已存在，请检验后填写");
            }
        }
        product.setUpdateTime(DateUtils.getNowDate());
        return productMapper.updateProduct(product);
    }

    /**
     * 批量删除商品表
     * 
     * @param ids 需要删除的商品表ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteProductByIds(Long[] ids)
    {
        for(Long id : ids){
            checkDelProduct(id);
        }
        return productMapper.deleteProductByIds(ids);
    }

    /**
     * 删除商品表信息
     * 
     * @param id 商品表ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteProductById(Long id)
    {
        checkDelProduct(id);
        return productMapper.deleteProductById(id);
    }

    /**
     * 校验是否可以删除商品
     * @param id
     */
    private void checkDelProduct(Long id) {
        PurchaseOrderProductRelation param = new PurchaseOrderProductRelation();
        param.setProductId(id);
        List<PurchaseOrderProductRelation> orderList = purchaseOrderProductRelationMapper.selectPurchaseOrderProductRelationList(param);
        if(!CollectionUtils.isEmpty(orderList)){
            throw new CustomException("该商品绑定有订单，请核实后删除");
        }
    }
}
