package com.wsx.friends.user.rpc.impl;

import org.springframework.stereotype.Service;

import com.wsx.friends.user.rpc.inter.UserCommonInterface;

@Service
public class UserCommonImpl implements UserCommonInterface {

	@Override
	public String get() {
		// TODO Auto-generated method stub
		return "hello";
	}


}
