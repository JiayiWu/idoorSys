package com.idoorSys.service;

import com.idoorSys.model.Reserve;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.idoorSys.utils.SpringContextsUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 定时清空本地预约信息，拉取预约网站信息
 */
@Service
public class TimerJobService extends QuartzJobBean {

//	ReserveService reserveService = (ReserveService) SpringContextsUtil
//			.getBean("reserveService");

	@Resource
	ReserveService service; //TODO service is null

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		service.cleanLocalReserve();
		service.SaveRemoteReserve();
	}

}
