package com.ld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ld.dao.PlayDao;

@Service
public class PlayService {
	@Autowired
	PlayDao playDao;
	public void updateOrderState(String oid) {
		playDao.updateOrderState(oid);
	}
}
