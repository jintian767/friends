package com.wsx.friends.core.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

/**
 * 对外提供接口使用，不验证session
 * @author wangshuaixin
 *
 */
@RestController
@Scope("prototype")
public abstract class BaseForeignController {

}
