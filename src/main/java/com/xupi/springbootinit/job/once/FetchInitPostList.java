package com.xupi.springbootinit.job.once;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xupi.springbootinit.esdao.PostEsDao;
import com.xupi.springbootinit.model.dto.post.PostEsDTO;
import com.xupi.springbootinit.model.entity.Post;
import com.xupi.springbootinit.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 获取帖子列表初始化数据
 *
 * @author <a href="https://github.com/liyupi">程序员小徐</a>
 * @from <a href="https://github.com/Xuyuyu520">编程学习</a>
 */
// todo 取消注释开启任务
@Component
@Slf4j
public class FetchInitPostList implements CommandLineRunner {

	@Resource
	private PostService postService;

	@Resource
	private PostEsDao postEsDao;

	@Override
	public void run(String... args) {
		// 1. 获取数据
		String json = "{\"current\":1,\"pageSize\":8,\"sortField\":\"createTime\",\"sortOrder\":\"descend\",\"category\":\"文章\",\"reviewStatus\":1}";
		String url = "https://www.code-nav.cn/api/post/search/page/vo";
		String result = HttpRequest
				.post(url)
				.body(json)
				.execute()
				.body();
//        System.out.println(result);
		// 2. json 转对象
		Map<String, Object> map = JSONUtil.toBean(result, Map.class);
		JSONObject data = (JSONObject) map.get("data");
		JSONArray records = (JSONArray) data.get("records");
		List<Post> postList = new ArrayList<>();
		for (Object record : records) {
			JSONObject tempRecord = (JSONObject) record;
			Post post = new Post();
			post.setTitle(tempRecord.getStr("title"));
			post.setContent(tempRecord.getStr("content"));
			JSONArray tags = (JSONArray) tempRecord.get("tags");
			List<String> tagList = tags.toList(String.class);
			post.setTags(JSONUtil.toJsonStr(tagList));
			post.setUserId(1L);
			postList.add(post);
		}
//        System.out.println(postList);
		// 3. 数据入库
		boolean b = postService.saveBatch(postList);
		if (b) {
			log.info("FetchInitPostList 获取帖子列表成功 数量= ", postList.size());
		} else {
			log.error("FetchInitPostList 获取帖子列表失败");
		}
	}
}
