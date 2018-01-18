package com.xingong.myks.webaction;

import com.google.gson.Gson;
import com.sun.javafx.collections.MappingChange;
import com.xingong.myks.commonutils.BaseResponse;
import com.xingong.myks.commonutils.ReturnInfo;
import com.xingong.myks.domain.OrderEntity;
import com.xingong.myks.services.AnalyseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 营业分析
 */

@Controller
@RequestMapping(value = "/analyse/")
public class AnalyseAction {

    @Autowired
    AnalyseService analyseService;

    Logger logger = Logger.getLogger(AnalyseAction.class);

    /**
     * 销售分析数据查询
     * @return
     */
    @RequestMapping(value = "analysebyorder",method = {RequestMethod.GET},produces = {"application/json;charset=UTF-8" })
    @ResponseBody
    public BaseResponse analybyorder(){

        BaseResponse baseResponse = new BaseResponse();

        try {
            Gson gson = new Gson();
            List<OrderEntity> orderlist = analyseService.analyByOrder();
            String responseData = gson.toJson(orderlist);
            baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_OK);
            baseResponse.setData(responseData);
            logger.info("销售分析数据查询成功");

        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return baseResponse;

    }

    /**
     * 房台分析数据查询
     * @return
     */
    @RequestMapping(value = "analysebyroom",method = {RequestMethod.GET},produces = {"application/json;charset=UTF-8" })
    @ResponseBody
    public BaseResponse analybyroom(){
        BaseResponse baseResponse = new BaseResponse();
        List<Map> responseList = new ArrayList<Map>();
        try {
            Gson gson = new Gson();
            List roomList = analyseService.analyByRoom();
            for (int i=0;i<roomList.size();i++){
                //强制类型转换。
                Object[] objarr = (Object[]) roomList.get(i);
                Map<String,String> itemMap = new HashMap<String, String>();
                    itemMap.put("roomid",objarr[0].toString());
                    itemMap.put("roommoney",objarr[1].toString());
                    responseList.add(itemMap);
            }


            String responseData = gson.toJson(responseList);
            baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_OK);
            baseResponse.setData(responseData);
            logger.info("房台分析数据查询成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_FAILURE);
            baseResponse.setMessage(e.getMessage());
        }
        return baseResponse;
    }

    /**
     * 员工业绩分析数据查询
     * @return
     */
    @RequestMapping(value = "analysebystaff",method = {RequestMethod.GET},produces = {"application/json;charset=UTF-8" })
    @ResponseBody
    public BaseResponse analybystaff(){
        BaseResponse baseResponse = new BaseResponse();
        List<Map> responseList = new ArrayList<Map>();

        try {
            Gson gson = new Gson();
            List staffList = analyseService.analyByStaff();
            for (int i=0;i<staffList.size();i++){
                //强制类型转换。
                Object[] objarr = (Object[]) staffList.get(i);
                Map<String,String> itemMap = new HashMap<String, String>();
                itemMap.put("staffid",objarr[0].toString());
                itemMap.put("salemoney",objarr[1].toString());
                responseList.add(itemMap);
            }
            String responseData = gson.toJson(responseList);
            baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_OK);
            baseResponse.setData(responseData);
            logger.info("员工绩效分析数据查询成功");
        }catch (Exception e){
            e.printStackTrace();
            baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_FAILURE);
            baseResponse.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }
        return baseResponse;
    }
}
