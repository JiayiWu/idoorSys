package com.idoorSys.service;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.annotation.Resource;

/**
 * 定时清空本地预约信息，拉取预约网站信息
 */
@Service
public class TimerJobService extends QuartzJobBean {

//	@Resource
//	ReserveService reserveService; //TODO reserveService is null
	@Resource
	SyncService syncService;

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		System.out.println(syncService == null);
//		reserveService.cleanLocalReserve();
//		reserveService.SaveRemoteReserve();
	}

}
