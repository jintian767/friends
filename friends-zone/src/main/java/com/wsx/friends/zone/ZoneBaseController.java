package com.wsx.friends.zone;

import org.springframework.context.annotation.Scope;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wsx.friends.core.controller.BaseController;
import com.wsx.friends.core.util.DateUtil;
import com.wsx.friends.zone.content.model.mongo.ZoneContent;

@RestController
@Scope("prototype")
@RequestMapping("/Zone")
public abstract class ZoneBaseController extends BaseController {

	protected boolean checkZoneContent(ZoneContent zoneContent) {
		if (StringUtils.isEmpty(zoneContent.getTitle())) {
			return false;
		}
		if (StringUtils.isEmpty(zoneContent.getContent())) {
			return false;
		}
		if (StringUtils.isEmpty(zoneContent.getPdate())) {
			zoneContent.setPdate(DateUtil.getNowDate());
		}
		return true;
	}
}
