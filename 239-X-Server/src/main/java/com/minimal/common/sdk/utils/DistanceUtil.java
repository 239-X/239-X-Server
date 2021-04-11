package com.minimal.common.sdk.utils;

/**
 * 距离计算工具
 * @author wubin
 *
 */
public class DistanceUtil {

	private static final double EARTH_RADIUS = 6378137;
	private static double rad(double d)
	{
	   return d * Math.PI / 180.0;
	}

	public static double getDistance(double lng1, double lat1, double lng2, double lat2)
	{
	   double radLat1 = rad(lat1);
	   double radLat2 = rad(lat2);
	   double a = radLat1 - radLat2;
	   double b = rad(lng1) - rad(lng2);
	   double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + 
	    Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
	   s = s * EARTH_RADIUS;
	   s = Math.round(s * 10000) / 10000;
	   return s;
	}
	public static void main(String[] args) {
		//西门口到公园前 835米
		System.out.println(DistanceUtil.getDistance(113.262457,23.131419,113.270614,23.131685));
	}
}
