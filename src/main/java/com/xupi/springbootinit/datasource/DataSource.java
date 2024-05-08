package com.xupi.springbootinit.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupi.springbootinit.model.entity.Picture;
import org.apache.poi.ss.formula.functions.T;

/**
 * @author: xuYuYu
 * @createTime: 2024/5/8 22:11
 * @Description: TODO 数据源接口
 */
public interface DataSource<T> {
	/**
	 * 搜索
	 *
	 * @param searchText
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Page<T> doSearch(String searchText, long pageNum, long pageSize);
}
