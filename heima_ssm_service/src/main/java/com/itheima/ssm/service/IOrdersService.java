package com.itheima.ssm.service;

import com.itheima.ssm.domain.Orders;

import java.util.List;

/**
 * 订单
 */
public interface IOrdersService {

    /**
     * 查找所有订单
     * @return
     */
    List<Orders> findAll(int page,int pageSize);

    /**
     * 根据id查找订单详情
     * @param ordersId
     * @return
     */
    Orders findById(String ordersId) throws Exception;
}
