package com.ld.utils;

import java.util.UUID;

public class CommonsUtils {
	//生成uid方法
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-","");
	}

}
