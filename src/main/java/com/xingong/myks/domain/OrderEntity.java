package com.xingong.myks.domain;

import javax.persistence.*;

/**
 * Created by zhanghongtao on 2017/12/15.
 */
@Entity
@Table(name = "order", schema = "ks", catalog = "")
public class OrderEntity {
    private Integer orderid;
    private String staffid;
    private Integer ordermoney;
    private String roomid;
    private String createdate;

    @Id
    @Column(name = "orderid", nullable = false)
    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    @Basic
    @Column(name = "staffid", nullable = true, length = 255)
    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }

    @Basic
    @Column(name = "ordermoney", nullable = true)
    public Integer getOrdermoney() {
        return ordermoney;
    }

    public void setOrdermoney(Integer ordermoney) {
        this.ordermoney = ordermoney;
    }

    @Basic
    @Column(name = "roomid", nullable = true, length = 255)
    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    @Basic
    @Column(name = "createdate", nullable = true, length = 255)
    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (orderid != that.orderid) return false;
        if (staffid != null ? !staffid.equals(that.staffid) : that.staffid != null) return false;
        if (ordermoney != null ? !ordermoney.equals(that.ordermoney) : that.ordermoney != null) return false;
        if (roomid != null ? !roomid.equals(that.roomid) : that.roomid != null) return false;
        if (createdate != null ? !createdate.equals(that.createdate) : that.createdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderid;
        result = 31 * result + (staffid != null ? staffid.hashCode() : 0);
        result = 31 * result + (ordermoney != null ? ordermoney.hashCode() : 0);
        result = 31 * result + (roomid != null ? roomid.hashCode() : 0);
        result = 31 * result + (createdate != null ? createdate.hashCode() : 0);
        return result;
    }
}
