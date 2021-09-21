package com.everisbootcamp.accountretire.Web;

import org.springframework.web.reactive.function.client.WebClient;

import com.everisbootcamp.accountretire.Constant.Constants;

public class Consumer { 
	 public static final WebClient webclientAccount = WebClient.create(Constants.Path.ACCOUNT_PATH);
}
