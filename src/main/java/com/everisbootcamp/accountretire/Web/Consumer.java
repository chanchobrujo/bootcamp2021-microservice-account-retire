package com.everisbootcamp.accountretire.Web;

import com.everisbootcamp.accountretire.Constant.Constants;
import org.springframework.web.reactive.function.client.WebClient;

public class Consumer {

    public static final WebClient webclientAccount = WebClient.create(
        Constants.Path.ACCOUNT_PATH
    );
}
