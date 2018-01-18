package com.xingong.myks.dao;

import com.xingong.myks.domain.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * get food class
 */
public interface FoodDao extends JpaRepository<FoodEntity,Integer>{

    //根据食物类别获取食物信息
    @Query("select t from FoodEntity t order by t.foodid")
    public List<FoodEntity> getFoods();

}
