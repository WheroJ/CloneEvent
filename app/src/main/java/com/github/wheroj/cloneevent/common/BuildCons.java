package com.github.wheroj.cloneevent.common;

import android.content.Context;

@SuppressWarnings("unused")
public class BuildCons {

	/**
	 * 当前是否是debug状态
	 */
	public final static boolean isDebug = false;

	/**
	 * 0：外网测试   1：外网    2:仿真服务器     3：测试状态left
	 */
	public final static int NET_TYPE = 0;
	/**
	 * 显示给用户的版本号
	 */
	public static String versionShow = "2.9.0";
	
	 /**
	  * 在登录页面显示
	  */
	public static String appName;

	public static Context mContext;

	public static String packageName;

	/**
	 * title中显示的文字格式
	 */
	public static String titleText;

	/**
	 * 是否显示title中的logo
	 */
	public static boolean isShowLog;

	public static int titleColor;


	public static void init(Context context) {
		mContext = context;
		packageName = context.getPackageName();
	}

	static {
		configAccordHospital();
	}

	public static void configAccordHospital() {
		titleText = "健康管理平台";
		isShowLog = true;
		appName = "健康重医";
		titleColor = Constant.GREEN;
	}
}
