package com.itheima.ssm.service;

import com.itheima.ssm.domain.Product;

import java.util.List;

/**
 * 产品业务层接口
 */
public interface IProductService {

    /**
     * 查找所有产品
     * @return
     * @throws Exception
     */
    List<Product> findAll() throws Exception;


    /**
     * 增加产品
     * @param product
     * @throws Exception
     */
    void saveProduct(Product product) throws  Exception;
}
