package com.wsx.friends.goods;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wsx.friends.core.controller.BaseController;

@RestController
@Scope("prototype")
@RequestMapping("/Goods")
public abstract class GoodsBaseController extends BaseController {

}
