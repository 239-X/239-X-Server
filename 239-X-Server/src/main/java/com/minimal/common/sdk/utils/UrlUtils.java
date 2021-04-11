package com.minimal.common.sdk.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * URL工具
 * 
 * @author wubin
 *
 */
public class UrlUtils {
	public static List<String> subUrl(List<String> urlList) {
		List<String> handlersController = new ArrayList<String>();
		String checkurl = null;
		int index1 = 0;
		int index2 = 0;
		for (int i = 0; i < urlList.size(); i++) {
			if (urlList.get(i).contains("/")) {
				index1 = urlList.get(i).indexOf("/");
				index2 = urlList.get(i).indexOf("/", index1 + 1);
				checkurl = urlList.get(i).substring(index1, index2);
				handlersController.add(checkurl);
			}
		}
		return handlersController;
	}
}
