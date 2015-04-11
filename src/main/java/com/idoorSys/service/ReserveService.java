package com.idoorSys.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.idoorSys.dao.BaseDao;
import com.idoorSys.dao.ReserveDao;
import com.idoorSys.model.Reserve;
import com.idoorSys.utils.Msg;

public class ReserveService extends BaseService {
	private static final Logger log = LoggerFactory
			.getLogger(ReserveService.class);

	public static void main(String args[]) {
		List<Reserve> reserves = new ReserveService().getRemoteReserve();
		for (Reserve reserve : reserves) {
			System.out.println(reserve);
		}
	}

	public Msg SaveRemoteReserve() {
		List<Reserve> reserves = getRemoteReserve();
		for (int i = 0; i < reserves.size(); i++) {
			Reserve reserve = reserves.get(i);
			getBaseDao().save(reserve);
		}
		return Msg.SUCCESS;
	}

	private String getRemoteReserveJson() {
		String jsonData = "";
		HttpClient client = new HttpClient();
		 HttpMethod method = new GetMethod(
		 "http://eelab.nju.edu.cn:780/today_order/");
//		 HttpMethod method = new
//		 GetMethod("http://10.225.111.40/today_order/");

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
				reserve.setLid(obj.getString("lid"));
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH-mm-ss");
				reserve.setEndTime(simpleDateFormat.parse((obj
						.getString("end_time"))));
				reserve.setBeginTime(simpleDateFormat.parse(obj
						.getString("begin_time")));
				reserve.setSeatId(obj.getString("seat_id"));
				reserve.setCardNum(obj.getString("card_number"));
				reserve.setOldId(obj.getString("oldid"));
				reserves.add(reserve);
			}

			// jsonObject.get
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reserves;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idoorSys.service.IdoorService#getAll()
	 */
	@Override
	public List<?> getAll() {
		return ((ReserveDao) getBaseDao()).getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idoorSys.service.IdoorService#preAdd()
	 */
	@Override
	public void preAdd() {
		// getBaseDao().save(new Reserve("fas", "411"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idoorSys.service.IdoorService#deleteById(long)
	 */
	@Override
	public Msg deleteById(long id) {
		return getBaseDao().deleteById(Reserve.class, id);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idoorSys.service.IdoorService#getbyId(long)
	 */
	@Override
	public Object getbyId(long id) {
		return getBaseDao().findById(Reserve.class, id);
	}

	public Msg cleanLocalReserve() {
		getBaseDao().clearTable(Reserve.class.getSimpleName());
		return null;
		// TODO Auto-generated method stub
	}

}
