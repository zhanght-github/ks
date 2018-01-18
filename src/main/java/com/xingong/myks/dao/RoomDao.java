package com.xingong.myks.dao;

import com.xingong.myks.domain.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 改变和获取房间可使用状态（暂时没添加此功能）
 */
public interface RoomDao extends JpaRepository<RoomEntity,Integer>{


    @Query("select t from RoomEntity t where t.roomid = ?1")
    public RoomEntity getRoomById(int roomid);

    //查询状态值是1 的房间
    @Query("select t from RoomEntity t where t.roomstate = 1")
    public List<RoomEntity> getAvaliableRoom();
}
