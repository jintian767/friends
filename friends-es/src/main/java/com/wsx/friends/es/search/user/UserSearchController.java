package com.wsx.friends.es.search.user;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wsx.friends.core.model.OutputData;
import com.wsx.friends.es.EsBaseController;
import com.wsx.friends.es.search.OutputDatas;
import com.wsx.friends.es.search.user.model.User;
import com.wsx.friends.es.search.user.model.UserOutputData;
import com.wsx.friends.es.search.user.service.UserSearchService;

@RestController
@Scope("prototype")
public class UserSearchController extends EsBaseController {
	
	private static final Logger log = LoggerFactory.getLogger(UserSearchController.class);

	@Autowired
	private UserSearchService userSearchService;
	
	@RequestMapping(
			value = "/search/user/{userName}/{token}",
			method = {RequestMethod.GET}
	)
	public @ResponseBody OutputData searchUser(@PathVariable("userName") String userName, 
			@PathVariable("token") String token) {
		log.info("into ");
		
		User user = null;
		if (!StringUtils.isEmpty(userName)) {
			user = userSearchService.getPerson(userName);
		} else {
			return new OutputData(CODE_ERROR_CLIENT, STATUS_ERROR_PARAM);
		}
		
		
		UserOutputData data = new UserOutputData();
		data.setUser(user);
		
		return data;
	}
	
	@RequestMapping(
			value = "/search/users/{userName}/{token}",
			method = {RequestMethod.GET}
	)
	public @ResponseBody OutputDatas searchUsers(@PathVariable("userName") String userName, 
			@PathVariable("token") String token) {
		
		List<Map<String, Object>> list = null;
		if (!StringUtils.isEmpty(userName)) {
			list = userSearchService.searchUsersByName(userName);
		}
		
		OutputDatas datas = new OutputDatas();
		datas.setDatas(list);
		return datas;
	}
}
