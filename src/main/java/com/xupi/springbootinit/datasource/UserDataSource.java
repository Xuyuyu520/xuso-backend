package com.xupi.springbootinit.datasource;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupi.springbootinit.common.ErrorCode;
import com.xupi.springbootinit.constant.CommonConstant;
import com.xupi.springbootinit.exception.BusinessException;
import com.xupi.springbootinit.mapper.UserMapper;
import com.xupi.springbootinit.model.dto.user.UserQueryRequest;
import com.xupi.springbootinit.model.entity.User;
import com.xupi.springbootinit.model.enums.UserRoleEnum;
import com.xupi.springbootinit.model.vo.LoginUserVO;
import com.xupi.springbootinit.model.vo.UserVO;
import com.xupi.springbootinit.service.UserService;
import com.xupi.springbootinit.utils.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.xupi.springbootinit.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户服务实现
 *
 * @author <a href="https://github.com/liyupi">程序员小徐</a>
 * @from <a href="https://github.com/Xuyuyu520">编程学习</a>
 */
@Service
@Slf4j
public class UserDataSource implements DataSource<UserVO> {

	@Resource
	private UserService userService;

	/**
	 * 搜索
	 *
	 * @param searchText
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public Page<UserVO> doSearch(String searchText, long pageNum, long pageSize) {
		UserQueryRequest userQueryRequest = new UserQueryRequest();
		userQueryRequest.setUserName(searchText);
		userQueryRequest.setCurrent((int) pageNum);
		userQueryRequest.setPageSize((int) pageSize);
		Page<UserVO> userVOPage = userService.listUserVOByPage(userQueryRequest);
		return userVOPage;

	}
}
