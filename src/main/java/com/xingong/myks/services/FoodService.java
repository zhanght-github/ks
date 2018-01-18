package com.xingong.myks.services;

import com.xingong.myks.dao.FoodDao;
import com.xingong.myks.domain.FoodEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜品浏览
 */

@Service
@Transactional
public class FoodService {

    @Autowired
    FoodDao foodDao;

    public List<FoodEntity> getFoods(){
        return foodDao.getFoods();
    }

    public void addFood(FoodEntity foodEntity){
        foodDao.save(foodEntity);
    }
}
