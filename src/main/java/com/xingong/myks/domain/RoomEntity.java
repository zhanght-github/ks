package com.xingong.myks.domain;

import javax.persistence.*;

/**
 * Created by zhanghongtao on 2017/12/16.
 */
@Entity
@Table(name = "room", schema = "ks", catalog = "")
public class RoomEntity {
    private int roomid;
    private String roomname;
    private Integer roomstate;

    @Id
    @Column(name = "roomid", nullable = false)
    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    @Basic
    @Column(name = "roomname", nullable = true, length = 255)
    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    @Basic
    @Column(name = "roomstate", nullable = true)
    public Integer getRoomstate() {
        return roomstate;
    }

    public void setRoomstate(Integer roomstate) {
        this.roomstate = roomstate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomEntity that = (RoomEntity) o;

        if (roomid != that.roomid) return false;
        if (roomname != null ? !roomname.equals(that.roomname) : that.roomname != null) return false;
        if (roomstate != null ? !roomstate.equals(that.roomstate) : that.roomstate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roomid;
        result = 31 * result + (roomname != null ? roomname.hashCode() : 0);
        result = 31 * result + (roomstate != null ? roomstate.hashCode() : 0);
        return result;
    }
}
