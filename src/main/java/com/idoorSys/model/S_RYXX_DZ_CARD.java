package com.idoorSys.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * 校园卡信息，由校园卡中心提供
 */
@Entity
public class S_RYXX_DZ_CARD {
    private String STUEMPNO;
    private String CUSTNAME;
    private char SEX;
    private String CARDPHYID;
    private char CARDSTATUS;
    private String DEPTCODE;
    private String RYLXDM;
    private String CARDUPDTIME;

    private int id;
    private Timestamp timetag;

    public String getSTUEMPNO() {
        return STUEMPNO;
    }

    public void setSTUEMPNO(String STUEMPNO) {
        this.STUEMPNO = STUEMPNO;
    }

    public String getCUSTNAME() {
        return CUSTNAME;
    }

    public void setCUSTNAME(String CUSTNAME) {
        this.CUSTNAME = CUSTNAME;
    }

    public char getSEX() {
        return SEX;
    }

    public void setSEX(char SEX) {
        this.SEX = SEX;
    }

    public String getCARDPHYID() {
        return CARDPHYID;
    }

    public void setCARDPHYID(String CARDPHYID) {
        this.CARDPHYID = CARDPHYID;
    }

    public char getCARDSTATUS() {
        return CARDSTATUS;
    }

    public void setCARDSTATUS(char CARDSTATUS) {
        this.CARDSTATUS = CARDSTATUS;
    }

    public String getDEPTCODE() {
        return DEPTCODE;
    }

    public void setDEPTCODE(String DEPTCODE) {
        this.DEPTCODE = DEPTCODE;
    }

    public String getRYLXDM() {
        return RYLXDM;
    }

    public void setRYLXDM(String RYLXDM) {
        this.RYLXDM = RYLXDM;
    }

    public String getCARDUPDTIME() {
        return CARDUPDTIME;
    }

    public void setCARDUPDTIME(String CARDUPDTIME) {
        this.CARDUPDTIME = CARDUPDTIME;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimetag() {
        return timetag;
    }

    public void setTimetag(Timestamp timetag) {
        this.timetag = timetag;
    }
}
