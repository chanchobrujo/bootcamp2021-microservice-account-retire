package com.everisbootcamp.accountretire;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class AccountRetireApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountRetireApplication.class, args);
        log.info("MICROSERVER RETIRE ENABLED");
    }
}
