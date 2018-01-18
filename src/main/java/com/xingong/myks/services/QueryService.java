package com.xingong.myks.services;

import com.xingong.myks.dao.OrderDao;
import com.xingong.myks.domain.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 根据时间查询订单
 */

@Service
@Transactional
public class QueryService {

    @Autowired
    OrderDao orderDao;

    public List<OrderEntity> queryOrder(String startdate,String enddate){
        return orderDao.queryOrder(startdate,enddate);
    }
}
