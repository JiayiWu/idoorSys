package com.idoorSys.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * 设备状态，用于表示房间设备状态，与嵌入式控制中心交互，实现对设备电源的控制
 */
public class DeviceState implements java.io.Serializable {
    public enum State {
        on, of
    }

    private String roomNo;
    private String frontDoorState;
    private String backDoorState;
    private String deskState;    // json map
    private String lightState;    // json map

    // State Setter
    public void setDeskState(String deskNo, State state) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> map = mapper.readValue(deskState, Map.class);
            map.put(deskNo, state.toString());
            this.deskState = mapper.writeValueAsString(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLightState(String lightNo, State state) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> map = mapper.readValue(lightState, Map.class);
            map.put(lightNo, state.toString());
            this.lightState = mapper.writeValueAsString(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // StateMap generator
    public Map<String, String> generateDeskStateMap() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = new TreeMap<>();
        try {
            map = mapper.readValue(deskState, TreeMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public Map<String, String> generateLightStateMap() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = new TreeMap<>();
        try {
            map = mapper.readValue(lightState, TreeMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public String getRoomNo() {
        return this.roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getFrontDoorState() {
        return this.frontDoorState;
    }

    public void setFrontDoorState(String frontDoorState) {
        this.frontDoorState = frontDoorState;
    }

    public String getBackDoorState() {
        return this.backDoorState;
    }

    public void setBackDoorState(String backDoorState) {
        this.backDoorState = backDoorState;
    }

    public String getDeskState() {
        return this.deskState;
    }

    public void setDeskState(String deskState) {
        this.deskState = deskState;
    }

    public String getLightState() {
        return this.lightState;
    }

    public void setLightState(String lightState) {
        this.lightState = lightState;
    }

}