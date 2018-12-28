package com.insigma.cloud.zuul;

import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.zuul.prc.admin.LogService;
import com.insigma.mvc.model.SErrorLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClouddoZuulApplicationTests {
	@Autowired
	LogService logService;
	@Test
	public void contextLoads() {

	}

}
