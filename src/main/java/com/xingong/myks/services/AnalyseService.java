package com.xingong.myks.services;

import com.xingong.myks.dao.OrderDao;
import com.xingong.myks.domain.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

@Service
@Transactional
public class AnalyseService {

    @Autowired
    OrderDao orderDao;

    /**
     * 销售分析
     * 一次查询七条（应该在dao里通过查询语句实现，但是。。。）
     * @return
     */
    public List<OrderEntity> analyByOrder(){
        List<OrderEntity> tempList = orderDao.analyByOrder();
        List<OrderEntity> orderList = new ArrayList<OrderEntity>();
        if (tempList.size()>6){
            for(int i=0;i<7;i++){
                orderList.add(tempList.get(i));
            }
            return orderList;
        }
        return tempList;
    }


    /**
     * 房台分析
     * @return
     */
    public List analyByRoom(){
        return orderDao.analyByRoom();
    }

    /**
     * 员工业绩分析
     * @return
     */
    public List analyByStaff(){
        return orderDao.analyByStaff();
    }
}
