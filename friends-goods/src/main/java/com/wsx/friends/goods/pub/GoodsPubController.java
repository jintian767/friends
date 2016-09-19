package com.wsx.friends.goods.pub;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import com.wsx.friends.goods.GoodsBaseController;

@RestController
@Scope("prototype")
public class GoodsPubController extends GoodsBaseController {

}
