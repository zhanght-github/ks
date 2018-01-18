package com.xingong.myks.services;

import com.xingong.myks.dao.OrderDao;
import com.xingong.myks.domain.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 添加订单
 */

@Service
@Transactional
public class AddorderService {

    @Autowired
    OrderDao orderDao;

    public void addOrder(OrderEntity orderEntity){
        orderDao.save(orderEntity);
    }
}
