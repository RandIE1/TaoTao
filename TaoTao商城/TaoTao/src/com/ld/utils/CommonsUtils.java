package com.ld.utils;

import java.util.UUID;

public class CommonsUtils {
	//����uid����
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-","");
	}

}
