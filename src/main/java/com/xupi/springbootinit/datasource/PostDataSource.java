package com.xupi.springbootinit.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupi.springbootinit.model.dto.post.PostQueryRequest;
import com.xupi.springbootinit.model.vo.PostVO;
import com.xupi.springbootinit.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 帖子服务实现
 *
 * @author <a href="https://github.com/liyupi">程序员小徐</a>
 * @from <a href="https://github.com/Xuyuyu520">编程学习</a>
 */
@Service
@Slf4j
public class PostDataSource implements DataSource<PostVO> {

	@Resource
	private PostService postService;


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
		PostQueryRequest postQueryRequest = new PostQueryRequest();
		postQueryRequest.setSearchText(searchText);
		postQueryRequest.setCurrent((int) pageNum);
		postQueryRequest.setPageSize((int) pageNum);
		Page<PostVO> postVOPage = postService.listPostVOByPage(postQueryRequest, null);
		return postVOPage;
	}
}




