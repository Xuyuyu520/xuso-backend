package com.xupi.springbootinit.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupi.springbootinit.annotation.AuthCheck;
import com.xupi.springbootinit.common.BaseResponse;
import com.xupi.springbootinit.common.DeleteRequest;
import com.xupi.springbootinit.common.ErrorCode;
import com.xupi.springbootinit.common.ResultUtils;
import com.xupi.springbootinit.constant.UserConstant;
import com.xupi.springbootinit.exception.BusinessException;
import com.xupi.springbootinit.exception.ThrowUtils;
import com.xupi.springbootinit.model.dto.picture.PictureQueryRequest;
import com.xupi.springbootinit.model.dto.post.PostAddRequest;
import com.xupi.springbootinit.model.dto.post.PostEditRequest;
import com.xupi.springbootinit.model.dto.post.PostQueryRequest;
import com.xupi.springbootinit.model.dto.post.PostUpdateRequest;
import com.xupi.springbootinit.model.entity.Picture;
import com.xupi.springbootinit.model.entity.Post;
import com.xupi.springbootinit.model.entity.User;
import com.xupi.springbootinit.model.vo.PostVO;
import com.xupi.springbootinit.service.PictureService;
import com.xupi.springbootinit.service.PostService;
import com.xupi.springbootinit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 图片接口
 *
 * @author <a href="https://github.com/liyupi">程序员小徐</a>
 * @from <a href="https://github.com/Xuyuyu520">编程学习</a>
 */
@RestController
@RequestMapping("/picture")
@Slf4j
public class PictureController {

	@Resource
	private PostService postService;

	@Resource
	private UserService userService;
	@Resource
	private PictureService pictureService;

	/**
	 * 分页获取列表（封装类）
	 *
	 * @param pictureQueryRequest
	 * @param request
	 * @return
	 */
	@PostMapping("/list/page/vo")
	public BaseResponse<Page<Picture>> listPictureVOByPage(@RequestBody PictureQueryRequest pictureQueryRequest,
	                                                       HttpServletRequest request) {
		long current = pictureQueryRequest.getCurrent();
		long size = pictureQueryRequest.getPageSize();
		// 限制爬虫
		ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
		Page<Picture> picturePage = pictureService.searchPicture(pictureQueryRequest.getSearchText(), current, size);
		return ResultUtils.success(picturePage);
	}


}
