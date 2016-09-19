package com.wsx.friends.language;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wsx.friends.core.controller.BaseController;
import com.wsx.friends.core.controller.WebController;

@RestController
@Scope("prototype")
@RequestMapping("/Language")
public class BaseLanguageController extends BaseController implements WebController {

}
