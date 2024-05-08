package com.xupi.springbootinit.model.dto.picture;

import com.xupi.springbootinit.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author: xuYuYu
 * @createTime: 2024/5/8 9:23
 * @Description: TODO 图片请求类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PictureQueryRequest extends PageRequest implements Serializable {
	/**
	 * 搜索词
	 */
	private String searchText;


}
