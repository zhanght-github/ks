package com.xingong.myks.dao;

import com.xingong.myks.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单
 */
public interface OrderDao extends JpaRepository<OrderEntity,Integer>{

    //销售分析
    @Query("select t.orderid,sum(t.ordermoney) from OrderEntity t group by t.orderid")
    public List<OrderEntity> analyByOrder();

    //房台分析
    @Query("select t.roomid,SUM(t.ordermoney) from OrderEntity t group by t.roomid")
    public List analyByRoom();

    //员工业绩分析
    @Query("select t.staffid,sum(t.ordermoney) from OrderEntity t group by t.staffid")
    public List analyByStaff();


    //根据时间查询订单
    @Query("select t from OrderEntity t where t.createdate >= ?1 and t.createdate < ?2")
    public List<OrderEntity> queryOrder(String startdate,String enddate);

}
