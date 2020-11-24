package com.ld.service;

import java.sql.SQLException;
import java.util.List;

import com.ld.DaoImp.CategoryDaoImp;
import com.ld.bean.Category;

public class CategoryService {

	public List<Category> findAllCategory() {
		// TODO Auto-generated method stub
		CategoryDaoImp dao = new CategoryDaoImp();
		List<Category> categoryList = null;
		try {
			categoryList = dao.findAllCategory();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoryList;
	}
	 

}
