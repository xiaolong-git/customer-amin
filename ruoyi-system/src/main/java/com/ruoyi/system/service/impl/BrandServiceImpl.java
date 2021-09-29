package com.ruoyi.system.service.impl;

import java.util.Collection;
import java.util.List;

import com.ruoyi.common.core.domain.entity.Brand;
import com.ruoyi.common.core.domain.entity.Product;
import com.ruoyi.common.core.domain.entity.PurchaseOrderProductRelation;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.mapper.BrandMapper;
import com.ruoyi.system.mapper.ProductMapper;
import com.ruoyi.system.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * 商品品牌表Service业务层处理
 * 
 * @author xl
 * @date 2021-04-06
 */
@Service
public class BrandServiceImpl implements IBrandService
{
    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询商品品牌表
     * 
     * @param id 商品品牌表ID
     * @return 商品品牌表
     */
    @Override
    public Brand selectBrandById(Long id)
    {
        return brandMapper.selectBrandById(id);
    }

    /**
     * 查询商品品牌表列表
     * 
     * @param brand 商品品牌表
     * @return 商品品牌表
     */
    @Override
    public List<Brand> selectBrandList(Brand brand)
    {
        return brandMapper.selectBrandList(brand);
    }

    /**
     * 新增商品品牌表
     * 
     * @param brand 商品品牌表
     * @return 结果
     */
    @Override
    public int insertBrand(Brand brand)
    {
        if(StringUtils.isEmpty(brand.getName())){
            throw new CustomException("请输入品牌名称");
        }

        //查询是否有重复名称的品牌
        Brand queryParam = new Brand();
        queryParam.setName(brand.getName());
        if(!CollectionUtils.isEmpty(brandMapper.selectBrandList(queryParam))){
            throw new CustomException("该品牌名称已存在，请检验后填写");
        }

        brand.setCreateBy(SecurityUtils.getUsername());
        brand.setCreateTime(DateUtils.getNowDate());
        brandMapper.insertBrand(brand);
        String id = brand.getId().toString();
        brand.setCode(StringUtils.getTableCode("BRAND-", id));
        return brandMapper.updateBrand(brand);
    }

    /**
     * 修改商品品牌表
     * 
     * @param brand 商品品牌表
     * @return 结果
     */
    @Override
    public int updateBrand(Brand brand)
    {
        brand.setUpdateTime(DateUtils.getNowDate());

        if(StringUtils.isEmpty(brand.getName())){
            throw new CustomException("请输入品牌名称");
        }
        //查询是否有重复名称的品牌
        Brand queryParam = new Brand();
        queryParam.setName(brand.getName());
        List<Brand> brandList = brandMapper.selectBrandList(queryParam);
        if(!CollectionUtils.isEmpty(brandList)){
            if(!brandList.get(0).getId().equals(brand.getId())){
                throw new CustomException("该品牌名称已存在，请检验后填写");
            }
        }

        return brandMapper.updateBrand(brand);
    }

    /**
     * 批量删除商品品牌表
     * 
     * @param ids 需要删除的商品品牌表ID
     * @return 结果
     */
    @Override
    public int deleteBrandByIds(Long[] ids)
    {
        for (Long id: ids) {
            checkDelBrand(id);
        }
        return brandMapper.deleteBrandByIds(ids);
    }

    /**
     * 删除商品品牌表信息
     * 
     * @param id 商品品牌表ID
     * @return 结果
     */
    @Override
    public int deleteBrandById(Long id)
    {
        checkDelBrand(id);
        return brandMapper.deleteBrandById(id);
    }

    /**
     * 校验是否可以删除品牌
     * @param id
     */
    private void checkDelBrand(Long id) {
        Product param = new Product();
        param.setBrandId(id);
        List<Product> orderList = productMapper.selectProductList(param);
        if(!CollectionUtils.isEmpty(orderList)){
            throw new CustomException("该品牌下有商品，请核实后删除");
        }
    }
}
