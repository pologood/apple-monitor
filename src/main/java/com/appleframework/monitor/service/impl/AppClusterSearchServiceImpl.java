package com.appleframework.monitor.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.appleframework.jmx.database.dao.AppClusterDao;
import com.appleframework.jmx.database.entity.AppClusterEntity;
import com.appleframework.model.Search;
import com.appleframework.model.page.Pagination;
import com.appleframework.monitor.service.AppClusterSearchService;

@Service("appClusterSearchService")
public class AppClusterSearchServiceImpl implements AppClusterSearchService {

	@Resource
	private AppClusterDao appClusterDao;
	
	public Pagination findPage(Pagination page, Search search) {
		List<AppClusterEntity> list = appClusterDao.findByPage(page, search);
		page.setList(list);
		return page;
	}
}
