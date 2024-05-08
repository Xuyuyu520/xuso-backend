package com.xupi.springbootinit.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author: xuYuYu
 * @createTime: 2024/5/8 22:44
 * @Description: TODO 视频数据源
 */
public class VideoDataSource implements DataSource<Object> {
	/**
	 * 搜索
	 *
	 * @param searchText
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public Page doSearch(String searchText, long pageNum, long pageSize) {
		return null;
	}
}
