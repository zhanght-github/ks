package com.xingong.myks.commonaction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xingong.myks.commonutils.BaseResponse;
import com.xingong.myks.commonutils.ReturnInfo;
import com.xingong.myks.domain.OrderEntity;
import com.xingong.myks.services.AddorderService;
import com.xingong.myks.services.RoomService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 添加订单结算的服务接口
 */

@Controller
@RequestMapping(value = "/order/")
public class AddorderAction {

    @Autowired
    AddorderService addorderService;

    @Autowired
    RoomService roomService;

    Logger logger = Logger.getLogger(AddorderAction.class);


    /**
     * 往库（order表）里添加结算订单
     * @param strRequest
     * @return
     */
    @RequestMapping(value = "addorder",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public BaseResponse addorder(@RequestBody String strRequest) {

       return orderMethod(strRequest);
    }


    /**
     * 往库（order表）里添加结算订单
     * @param strRequest
     * @return
     */
    @RequestMapping(value = "commitorder",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public BaseResponse commitorder(String strRequest) {

        return orderMethod(strRequest);
    }


    public BaseResponse orderMethod(String strRequest){
        BaseResponse baseResponse = new BaseResponse();
        Gson gson = new Gson();
        try {
            OrderEntity orderEntity = gson.fromJson(strRequest,OrderEntity.class);
            if (orderEntity.getOrdermoney() == 0){
                baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_FAILURE);
                baseResponse.setMessage("订单为空");
                logger.error("提交的订单为空");
            }else {
                //判断，如果roomid不是0，那就是包间，调用方法改变包间状态
                int roomid = Integer.parseInt(orderEntity.getRoomid());
                if (roomid != 0){
                    roomService.setRoomState(roomid,0);
                }
                addorderService.addOrder(orderEntity);
                baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_OK);
                baseResponse.setMessage("结算成功");
                logger.info("提交订单结算成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_FAILURE);
            baseResponse.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }
        return baseResponse;
    }

}
