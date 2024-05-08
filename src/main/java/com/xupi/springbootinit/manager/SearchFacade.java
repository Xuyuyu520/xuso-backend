package com.xupi.springbootinit.manager;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupi.springbootinit.common.ErrorCode;
import com.xupi.springbootinit.datasource.DataSource;
import com.xupi.springbootinit.datasource.PictureDataSource;
import com.xupi.springbootinit.datasource.PostDataSource;
import com.xupi.springbootinit.datasource.UserDataSource;
import com.xupi.springbootinit.exception.BusinessException;
import com.xupi.springbootinit.exception.ThrowUtils;
import com.xupi.springbootinit.model.dto.post.PostQueryRequest;
import com.xupi.springbootinit.model.dto.search.SearchRequest;
import com.xupi.springbootinit.model.dto.user.UserQueryRequest;
import com.xupi.springbootinit.model.entity.Picture;
import com.xupi.springbootinit.model.enums.SearchTypeEnum;
import com.xupi.springbootinit.model.vo.PostVO;
import com.xupi.springbootinit.model.vo.SearchVO;
import com.xupi.springbootinit.model.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

/**
 * @author: xuYuYu
 * @createTime: 2024/5/8 21:34
 * @Description: TODO 搜索门面模式
 */
@Slf4j
@Component
public class SearchFacade {

	@Resource
	private UserDataSource userDataSource;
	@Resource
	private PostDataSource postDataSource;
	@Resource
	private PictureDataSource pictureDataSource;

	public SearchVO searchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
		String type = searchRequest.getType();
		SearchTypeEnum searchTypeEnum = SearchTypeEnum.getEnumByValue(type);
		ThrowUtils.throwIf(StringUtils.isBlank(type), ErrorCode.PARAMS_ERROR);
		// 搜索条件
		String searchText = searchRequest.getSearchText();
		int current = searchRequest.getCurrent();
		int pageSize = searchRequest.getPageSize();
		if (searchTypeEnum == null) {
			// 用户类型
			CompletableFuture<Page<UserVO>> userTask = CompletableFuture.supplyAsync(() -> {
				UserQueryRequest userQueryRequest = new UserQueryRequest();
				userQueryRequest.setUserName(searchText);
				Page<UserVO> userVOPage = userDataSource.doSearch(searchText, current, pageSize);
				return userVOPage;
			});
			// 帖子类型
			CompletableFuture<Page<PostVO>> postTask = CompletableFuture.supplyAsync(() -> {
				PostQueryRequest postQueryRequest = new PostQueryRequest();
				Page<PostVO> postVOPage = postDataSource.doSearch(searchText, current, pageSize);
				return postVOPage;
			});
			// 图片类型
			CompletableFuture<Page<Picture>> pictureTask = CompletableFuture.supplyAsync(() -> {
				Page<Picture> picturePage = pictureDataSource.doSearch(searchText, 1, 12);
				return picturePage;
			});
			// 异步
			try {
				CompletableFuture.allOf(userTask, postTask, pictureTask).join();
				Page<UserVO> userVOPage = userTask.get();
				Page<Picture> picturePage = pictureTask.get();
				Page<PostVO> postVOPage = postTask.get();
				// 设置条件
				SearchVO searchVO = new SearchVO();
				searchVO.setUserList(userVOPage.getRecords());
				searchVO.setPostList(postVOPage.getRecords());
				searchVO.setPictureList(picturePage.getRecords());

				return searchVO;
			} catch (Exception e) {
				throw new BusinessException(ErrorCode.SYSTEM_ERROR, "查询失败");
			}
		} else {
			HashMap<String, DataSource<T>> typeDataSource = new HashMap() {{
				put(SearchTypeEnum.POST.getValue(), postDataSource);
				put(SearchTypeEnum.USER.getValue(), userDataSource);
				put(SearchTypeEnum.PICTURE.getValue(), pictureDataSource);
			}};
			// 设置条件
			SearchVO searchVO = new SearchVO();
			DataSource dataSource = typeDataSource.get(type);
			Page page = dataSource.doSearch(searchText, current, pageSize);
			searchVO.setDataList(page.getRecords());
			return searchVO;
		}
	}
}
