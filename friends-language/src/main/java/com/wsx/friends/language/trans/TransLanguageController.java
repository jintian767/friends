package com.wsx.friends.language.trans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wsx.friends.core.model.OutputData;
import com.wsx.friends.language.BaseLanguageController;

@RestController
@Scope("prototype")
public class TransLanguageController extends BaseLanguageController {
	
	private static final Logger log = LoggerFactory.getLogger(TransLanguageController.class);
	
	@RequestMapping(
			value = "/tans/to/en",
			method = {RequestMethod.POST}
	)
	public OutputData transLanguage() {
		log.info("into");
		
		return null;
	}

}
