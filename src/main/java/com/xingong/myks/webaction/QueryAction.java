package com.xingong.myks.webaction;

import com.google.gson.Gson;
import com.xingong.myks.commonutils.BaseResponse;
import com.xingong.myks.commonutils.ReturnInfo;
import com.xingong.myks.domain.OrderEntity;
import com.xingong.myks.services.QueryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 订单查询
 */

@Controller
@RequestMapping(value = "/query/")
public class QueryAction {

    @Autowired
    QueryService queryService;

    Logger logger = Logger.getLogger(AnalyseAction.class);

    /**
     * 根据时间查询订单
     * @return
     */
    @RequestMapping(value = "queryorder",method = {RequestMethod.GET},produces = {"application/json;charset=UTF-8" })
    @ResponseBody
    public BaseResponse queryOrder(String startdate,String enddate){

        BaseResponse baseResponse = new BaseResponse();
        Gson gson = new Gson();
        try {
            List<OrderEntity> orderList = queryService.queryOrder(startdate, enddate);
            if (orderList.size()>0){
                String responseData = gson.toJson(orderList);
                baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_OK);
                baseResponse.setData(responseData);
            }else {
                baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_FAILURE);
                baseResponse.setMessage("查询结果为空");
            }
        }catch (Exception e){
            e.printStackTrace();
            baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_FAILURE);
            baseResponse.setMessage(e.getMessage());
        }
        return baseResponse;
    }
}
