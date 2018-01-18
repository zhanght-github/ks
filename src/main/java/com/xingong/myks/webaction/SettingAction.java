package com.xingong.myks.webaction;

import com.google.gson.Gson;
import com.xingong.myks.commonutils.BaseResponse;
import com.xingong.myks.commonutils.ReturnInfo;
import com.xingong.myks.domain.FoodEntity;
import com.xingong.myks.domain.RoomEntity;
import com.xingong.myks.services.FoodService;
import com.xingong.myks.services.RoomService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 营业设置
 */

@Controller
@RequestMapping(value = "/setting/")
public class SettingAction {

    @Autowired
    FoodService foodService;

    @Autowired
    RoomService roomService;

    Logger logger = Logger.getLogger(SettingAction.class);

    /**
     * 酒菜设置之添加酒菜
     * @param strFood
     * @return
     */
    @RequestMapping(value = "addfood",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public BaseResponse addfood(@RequestBody String strFood){

        BaseResponse baseResponse = new BaseResponse();
        Gson gson = new Gson();
        try {
            FoodEntity foodEntity = gson.fromJson(strFood,FoodEntity.class);
            if (!foodEntity.getFoodname().equals("")){
                foodService.addFood(foodEntity);
                baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_OK);
                baseResponse.setMessage("添加成功");
                logger.info("添加酒菜成功");
            }else {
                baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_FAILURE);
                baseResponse.setMessage("添加信息为空");
                logger.error("添加酒菜信息为空");
            }

        }catch (Exception e){
            e.printStackTrace();
            baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_FAILURE);
            baseResponse.setMessage(e.getMessage());
        }
        return baseResponse;
    }

    /**
     * 修改房间可使用状态(web端使用)
     * @return
     */
    @RequestMapping(value = "roomsetting",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public BaseResponse roomsetting(@RequestBody String strrequest){

        //返回数据类
        BaseResponse baseResponse = new BaseResponse();

        Gson gson = new Gson();
        try {
            RoomEntity roomEntity = gson.fromJson(strrequest,RoomEntity.class);
            int roomid = roomEntity.getRoomid();
            int roomstate = roomEntity.getRoomstate();
            roomService.setRoomState(roomid,roomstate);
            baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_OK);
            baseResponse.setMessage("修改成功");
            logger.info("房间状态修改成功");
        }catch (Exception e){
            e.printStackTrace();
            baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_FAILURE);
            baseResponse.setMessage(e.getMessage());
        }
        return baseResponse;
    }
}
