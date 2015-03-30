package com.idoorSys.service;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.idoorSys.utils.SpringContextsUtil;

public class TimerJobService extends QuartzJobBean {

	ReserveService reserveService = (ReserveService) SpringContextsUtil
			.getBean("reserveService");

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		reserveService.cleanLocalReserve();
		reserveService.SaveRemoteReserve();
	}

}
