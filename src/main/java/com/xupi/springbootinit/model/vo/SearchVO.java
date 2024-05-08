package com.xupi.springbootinit.model.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupi.springbootinit.model.entity.Picture;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 聚合搜索
 *
 * @author <a href="https://github.com/liyupi">程序员小徐</a>
 * @from <a href="https://github.com/Xuyuyu520">编程学习</a>
 */
@Data
public class SearchVO implements Serializable {
	/** 用户列表 */
	private List<UserVO> userList;
	/** 帖子列表 */
	private List<PostVO> postList;
	/** 图片列表 */
	private List<Picture> pictureList;

	private List<Object> dataList;
}
