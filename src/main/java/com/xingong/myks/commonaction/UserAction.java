package com.xingong.myks.commonaction;

import com.google.gson.Gson;
import com.xingong.myks.commonutils.BaseResponse;
import com.xingong.myks.commonutils.ReturnInfo;
import com.xingong.myks.domain.UserEntity;
import com.xingong.myks.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * web端 用户操作控制类
 */

@Controller
@RequestMapping(value = "/user/")
public class UserAction {

    @Autowired
    UserService userService;

    //打印log
    private Logger logger = Logger.getLogger(UserAction.class);

    /**
     * 员工登录方法
     * @param username
     * @return
     */
    @RequestMapping(value = "login",method = {RequestMethod.GET,RequestMethod.POST},produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public BaseResponse login(String username, String password){

        BaseResponse baseResponse = new BaseResponse();

        try {

            UserEntity userInfo = userService.getUserInfo(username);
            if (userInfo == null){
                baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_FAILURE);
                baseResponse.setMessage("账号错误！");
                logger.error("username error");
                return baseResponse;
            }

            if (!password.equals(userInfo.getPassword())){
                baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_FAILURE);
                baseResponse.setMessage("密码错误！");
                logger.error("password error");
                return baseResponse;
            }

            baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_OK);
            baseResponse.setMessage("登录成功！");
            logger.info("login success");
        }catch (Exception e){
            baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_FAILURE);
            baseResponse.setMessage(e.getMessage());
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return baseResponse;

    }

    /**
     * 得到用户信息的方法
     * @param username
     * @return
     */
    @RequestMapping(value = "getuserinfo",method = {RequestMethod.GET},produces = {"application/json;charset=UTF-8" })
    @ResponseBody
    public BaseResponse getUserInfo(String username){
        BaseResponse baseResponse = new BaseResponse();
        try {
            Gson gson = new Gson();
            UserEntity userInfo = userService.getUserInfo(username);
            if (userInfo == null){
                baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_FAILURE);
                baseResponse.setMessage("未查到用户信息！");
                logger.error("not found userinfo");
                return baseResponse;
            }else {
                baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_OK);
                baseResponse.setMessage("查询成功");
                logger.info("query success");
                //使用Gson的toJson方法把对象转成json字符串
                String responseData = gson.toJson(userInfo);
                baseResponse.setData(responseData);
                return baseResponse;
            }
        }catch (Exception e){

            baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_FAILURE);
            baseResponse.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return baseResponse;
    }


    /**
     * 获取所有员工信息
     * @return
     */
    @RequestMapping(value = "getalluser",method = {RequestMethod.GET},produces = {"application/json;charset=UTF-8" })
    @ResponseBody
    public BaseResponse getAllInfo(){
        BaseResponse baseResponse = new BaseResponse();
        try {
            Gson gson = new Gson();
            List<UserEntity> usersInfo = userService.getAllUser();
            if (usersInfo.size() == 0){
                baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_FAILURE);
                baseResponse.setMessage("未查到用户信息！");
                logger.error("not found userinfo");
                return baseResponse;
            }else {
                baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_OK);
                logger.info("query success");
                //使用Gson的toJson方法把对象转成json字符串
                String responseData = gson.toJson(usersInfo);
                baseResponse.setData(responseData);
                return baseResponse;
            }
        }catch (Exception e){

            baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_FAILURE);
            baseResponse.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return baseResponse;
    }


    /**
     * 设置员工角色
     * @param userid
     * @param role
     * @return
     */
    @RequestMapping(value = "setuserrole",method = {RequestMethod.GET,RequestMethod.POST},produces = {"application/json;charset=UTF-8" })
    @ResponseBody
    public BaseResponse setUserRole(String loginusername,int userid,String role){
        BaseResponse baseResponse = new BaseResponse();
        try {
            UserEntity userEntity = userService.getUserInfo(loginusername);
            if (!userEntity.getRole().equals("p3")){
                baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_FAILURE);
                baseResponse.setMessage("您没有权限进行此操作");
            }else {
                userService.setUserRole(userid,role);
                baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_OK);
                baseResponse.setMessage("员工编号"+userid+"角色设置成功");
                logger.info("query success");
                return baseResponse;
            }

        }catch (Exception e){

            baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_FAILURE);
            baseResponse.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return baseResponse;
    }


    /**
     * 修改密码
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "modifypassword",method = {RequestMethod.GET,RequestMethod.POST},produces = {"application/json;charset=UTF-8" })
    @ResponseBody
    public BaseResponse modifyPassword(String username,String password){

        BaseResponse baseResponse = new BaseResponse();
        try {
            userService.modifyPassword(password, username);
            baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_OK);
            baseResponse.setMessage("修改成功！");
            logger.info("modify success");
        }catch (Exception e){
            baseResponse.setStatus(ReturnInfo.RESPONSE_STATUS_FAILURE);
            baseResponse.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }
        return baseResponse;
    }
}
