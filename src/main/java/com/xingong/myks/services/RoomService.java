package com.xingong.myks.services;

import com.xingong.myks.dao.RoomDao;
import com.xingong.myks.domain.RoomEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */

@Service
@Transactional
public class RoomService {

    @Autowired
    RoomDao roomDao;

    //改变房间状态(在settingAction中使用)
    public void setRoomState(int roomid,int roomstate){
        RoomEntity roomEntity = roomDao.getRoomById(roomid);
        roomEntity.setRoomstate(roomstate);
        roomDao.save(roomEntity);
    }

    //获取可用房间（在roomAction中使用）
    public List<RoomEntity> getAvaliableRoom(){
        return roomDao.getAvaliableRoom();
    }
}
