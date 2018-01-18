package com.xingong.myks.domain;

import javax.persistence.*;

/**
 * Created by zhanghongtao on 2017/12/18.
 */
@Entity
@Table(name = "food", schema = "ks", catalog = "")
public class FoodEntity {
    private int foodid;
    private String foodname;
    private Integer foodprice;

    @Id
    @Column(name = "foodid", nullable = false)
    public int getFoodid() {
        return foodid;
    }

    public void setFoodid(int foodid) {
        this.foodid = foodid;
    }

    @Basic
    @Column(name = "foodname", nullable = false, length = -1)
    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    @Basic
    @Column(name = "foodprice", nullable = true, precision = 0)
    public Integer getFoodprice() {
        return foodprice;
    }

    public void setFoodprice(Integer foodprice) {
        this.foodprice = foodprice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoodEntity that = (FoodEntity) o;

        if (foodid != that.foodid) return false;
        if (foodname != null ? !foodname.equals(that.foodname) : that.foodname != null) return false;
        if (foodprice != null ? !foodprice.equals(that.foodprice) : that.foodprice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = foodid;
        result = 31 * result + (foodname != null ? foodname.hashCode() : 0);
        result = 31 * result + (foodprice != null ? foodprice.hashCode() : 0);
        return result;
    }
}
