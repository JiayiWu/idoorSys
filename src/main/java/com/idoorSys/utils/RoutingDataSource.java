package com.idoorSys.utils;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {
	
	private static final ThreadLocal<String> holder = new ThreadLocal<String>();

	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return holder.get();
	}
	
	public static void clear(){
        holder.remove();
    }

	public static void setDataSourceKey(String key){
        holder.set(key);
    }

}
