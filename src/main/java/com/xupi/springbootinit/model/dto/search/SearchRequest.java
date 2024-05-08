package com.xupi.springbootinit.model.dto.search;

import com.xupi.springbootinit.common.PageRequest;
import com.xupi.springbootinit.model.enums.SearchTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author: xuYuYu
 * @createTime: 2024/5/8 9:23
 * @Description: TODO 查询请求类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SearchRequest extends PageRequest implements Serializable {
	/**
	 * 搜索词
	 */
	private String searchText;

	/** 类型 */
	private String type;


}
