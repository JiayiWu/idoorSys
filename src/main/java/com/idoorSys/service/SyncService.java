package com.idoorSys.service;

import com.idoorSys.dao.RemoteControllerDao;
import com.idoorSys.utils.Syncronizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Date;

/**
 * 与所有RemoteController同步数据，向其同步对应管辖范围内的房间及三种权限信息
 * Created by Ezio on 7/16/2015.
 */
@Service
public class SyncService {
    public void say() {
        System.out.println("I Can Sync");
    }
    private static final Logger log = LoggerFactory.getLogger(SyncService.class);
    @Resource
    private RemoteControllerDao dao;

    public void syncNow() {
        log.info("start sync now");
        try {
            Syncronizer syncronizer = Syncronizer.create("idoorsys", "root", "sqj");
            for (String ip: dao.getIpList()) {
                log.info("start sync " + ip);
                try {
                    syncronizer.sync(ip);
                } catch (SQLException e) {
                    log.error("Syncronize Fail: "+new Date().toString()+" on ip: "+ip);
                    log.error("Syncronize Exception: "+new Date().toString()+"\n" +
                                    "message: " + e.getMessage()+
                                    "SQLState: "+e.getSQLState()
                    );
                }
                log.info("done");
            }
            syncronizer.close();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Syncronize Exception: " + new Date().toString() + "\n" +
                            "message: " + e.getMessage() +
                            "SQLState: " + e.getSQLState()
            );
        }
    }

}
