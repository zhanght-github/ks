package com.xingong.myks.commonaction;

import com.google.gson.Gson;
import com.xingong.myks.commonutils.BaseResponse;
import com.xingong.myks.commonutils.ReturnInfo;
import com.xingong.myks.domain.RoomEntity;
import com.xingong.myks.services.RoomService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 */

@Controller
@RequestMapping(value = "/room/")
public class RoomAction {

    @Autowired
    RoomService roomService;

    Logger logger = Logger.getLogger(RoomAction.class);

    /**
     * 获取可用房间
     * @return
     */
    @RequestMapping(value = "getroom",method = {RequestMethod.GET},produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public BaseResponse getroom(){
        BaseResponse baseResponse = new BaseResponse();
        Gson gson = new Gson();
        try {
            List<RoomEntity> roomList = roomService.getAvaliableRoom();
            if (roomList.size() != 0){
                String responseData = gson.toJson(roomList);
                baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_OK);
                baseResponse.setData(responseData);
            }else {
                baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_OK);
                baseResponse.setMessage("没有可用房间");
            }
        }catch (Exception e){
            e.printStackTrace();
            baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_FAILURE);
            baseResponse.setMessage(e.getMessage());
        }
        return baseResponse;
    }


    /**
     * 修改房间可使用状态(安卓端调用，和web端的差别是参数不带房间名字)
     * @return
     */
    @RequestMapping(value = "setroomstate",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public BaseResponse roomsetting(int roomid,int roomstate){

        //返回数据类
        BaseResponse baseResponse = new BaseResponse();

        try {
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
