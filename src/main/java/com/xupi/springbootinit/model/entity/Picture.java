package com.xupi.springbootinit.model.entity;

import lombok.Data;

/**
 * @author: xuYuYu
 * @createTime: 2024/5/8 9:12
 * @Description: TODO  图片 对应实体 （爬虫抓取）
 */
@Data
public class Picture {

	/** 标题 */
	private String title;

	/** 网址 */
	private String url;
}
