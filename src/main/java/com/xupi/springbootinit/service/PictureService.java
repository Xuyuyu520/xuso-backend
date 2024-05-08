package com.xupi.springbootinit.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupi.springbootinit.model.entity.Picture;

/**
 * 图片服务
 *
 * @author <a href="https://github.com/liyupi">程序员小徐</a>
 * @from <a href="https://github.com/Xuyuyu520">编程学习</a>
 */
public interface PictureService {
	Page<Picture> searchPicture(String searchText, long pageNum, long pageSize);
}
