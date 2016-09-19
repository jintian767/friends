package com.wsx.friends.zone.content;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wsx.friends.zone.ZoneBaseController;
import com.wsx.friends.zone.content.model.mongo.ZoneContent;
import com.wsx.friends.zone.content.service.ZoneContentService;

/**
 * 
 * @author wangshuaixin
 *
 */
@RestController
@Scope("prototype")
public class ZoneContentController extends ZoneBaseController {
	
	@Autowired
	private ZoneContentService zoneContentService;

	@RequestMapping(
			value = "/content/publish",
			method = {RequestMethod.POST}
	)
	public String publishContent(ZoneContent zoneContent, HttpServletRequest request, HttpServletResponse response) {
		
		if (!checkZoneContent(zoneContent)) {
			throw new RuntimeException("null");
		}
		
		boolean isSave = zoneContentService.saveZoneContent(zoneContent);
		
		if (!isSave) {
			return "error";
		}
		
		return "WEB-INF/jsp/zoneContent/zone_content";
	}
}
