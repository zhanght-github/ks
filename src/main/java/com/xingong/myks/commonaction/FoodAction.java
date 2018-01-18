package com.xingong.myks.commonaction;

import com.google.gson.Gson;
import com.xingong.myks.commonutils.BaseResponse;
import com.xingong.myks.commonutils.ReturnInfo;
import com.xingong.myks.domain.FoodEntity;
import com.xingong.myks.services.FoodService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 菜品浏览
 */
@Controller
@RequestMapping(value = "/food/")
public class FoodAction {

    @Autowired
    FoodService foodService;

    //打印日志
    private Logger logger = Logger.getLogger(FoodAction.class);

    /**
     * 根据食物类别查询食物列表
     * @para
     * @return
     */
    @RequestMapping(value = "getfoods",method = {RequestMethod.GET,RequestMethod.POST},produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public BaseResponse getfoodinfo(){

        BaseResponse baseResponse = new BaseResponse();
        try {
            Gson gson = new Gson();
            List<FoodEntity> foodlist = foodService.getFoods();
            if (foodlist.size() != 0){
                String responsData = gson.toJson(foodlist);
                baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_OK);
                baseResponse.setData(responsData);
                logger.info("数据查询成功");
            }else {
                baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_FAILURE);
                baseResponse.setMessage("查询数据为空");
                baseResponse.setData("");
                logger.info("查询数据为空");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return baseResponse;
    }
}
