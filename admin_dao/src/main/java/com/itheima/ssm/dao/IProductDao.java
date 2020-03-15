package com.itheima.ssm.dao;

import com.itheima.ssm.ssm.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品实现接口
 */
@Repository
public interface IProductDao {

    /**
     * 查找所有产品
     * @return
     */
    @Select("select * from product")
    List<Product> findAll() throws Exception;

    /**
     * 根据id查询产品
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from product where id = #{id}")
    Product findById(String id) throws Exception;

    /**
     * 增加产品
     * @param product
     */
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void saveProduct(Product product);

}
