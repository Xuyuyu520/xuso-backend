package com.xupi.springbootinit.controller;

import com.xupi.springbootinit.common.BaseResponse;
import com.xupi.springbootinit.common.ResultUtils;
import com.xupi.springbootinit.manager.SearchFacade;
import com.xupi.springbootinit.model.dto.search.SearchRequest;
import com.xupi.springbootinit.model.vo.SearchVO;
import com.xupi.springbootinit.service.PictureService;
import com.xupi.springbootinit.service.PostService;
import com.xupi.springbootinit.service.UserService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 查询接口
 *
 * @author <a href="https://github.com/liyupi">程序员小徐</a>
 * @from <a href="https://github.com/Xuyuyu520">编程学习</a>
 */
@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {

	@Resource
	private UserService userService;
	@Resource
	private PostService postService;
	@Resource
	private PictureService pictureService;
	@Resource
	private SearchFacade searchFacade;

	@PostMapping("/all")
	public BaseResponse<SearchVO> searchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
		return ResultUtils.success(searchFacade.searchAll(searchRequest, request));
	}
}
