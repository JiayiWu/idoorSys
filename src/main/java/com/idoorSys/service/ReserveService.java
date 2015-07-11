package com.idoorSys.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idoorSys.dao.ReserveDao;
import com.idoorSys.model.Reserve;
import com.idoorSys.utils.Msg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 对预约数据的拉取与保存
 */
@Service
public class ReserveService {
	private static final Logger log = LoggerFactory
			.getLogger(ReserveService.class);
	@Resource
	private ReserveDao dao;

	public Msg SaveRemoteReserve() {
		List<Reserve> reserves = getRemoteReserve();
		for (int i = 0; i < reserves.size(); i++) {
			Reserve reserve = reserves.get(i);
			dao.save(reserve);
		}
		return Msg.SUCCESS;
	}

	private String getRemoteReserveJson() {
		String jsonData = "";
		HttpClient client = new HttpClient();
//		 HttpMethod method = new GetMethod(
//		 "http://eelab.nju.edu.cn:780/today_order/");
		 HttpMethod method = new
		 GetMethod("http://10.225.111.40/today_order/");

		try {
			client.executeMethod(method);
			jsonData = method.getResponseBodyAsString();
			jsonData = "{'data':" + jsonData + "}";
			log.debug("jsonData:" + jsonData);
			System.out.println(jsonData);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonData;
	}

	private List<Reserve> getRemoteReserve() {
		List<Reserve> reserves = new ArrayList<Reserve>();
		// String jsonData = "{'a':[{'lid': '1',"
		// + " 'end_time': '2015-03-05 11-00-00',"
		// + " 'begin_time': '2015-03-05 08-00-00'," + " 'seat_id': 1, "
		// + "'card_number': '11223344', " + "'oldid': 34}]}";
		String jsonData = getRemoteReserveJson();
		try {
			JSONObject jsonObject = new JSONObject(jsonData);
			JSONArray arr = jsonObject.getJSONArray("data");
			for (int i = 0; i < arr.length(); i++) {
				Reserve reserve = new Reserve();
				JSONObject obj = arr.getJSONObject(i);
				reserve.setRoom_num(obj.getString("lid"));
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH-mm-ss");
				reserve.setEnd_time(simpleDateFormat.parse((obj
						.getString("end_time"))));
				reserve.setBegin_time(simpleDateFormat.parse(obj
						.getString("begin_time")));
				reserve.setSeat_id(obj.getString("seat_id"));
				reserve.setCard_num(obj.getString("card_number"));
				reserve.setOld_id(obj.getString("oldid"));
				reserves.add(reserve);
			}

			// jsonObject.get
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reserves;
	}

	public List<?> getAll() {
		return dao.getAll();
	}

	public Msg deleteById(int id) {
		return dao.deleteById(Reserve.class, id);

	}

	public Object getbyId(int id) {
		return dao.findById(Reserve.class, id);
	}

	public Msg cleanLocalReserve() {
		dao.clearTable("reserve");
		return null;
	}

}
