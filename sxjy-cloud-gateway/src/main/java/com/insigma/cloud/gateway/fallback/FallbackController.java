package com.insigma.cloud.gateway.fallback;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wengsh on 2019/3/22.
 */
@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public String fallback() {
        return "Hello World!\nfrom gateway";
    }

}