package com.insigma.mockserver;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class MockServer {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        configureFor(8080);
        removeAllMappings();

        mock("/order/1", "user.json");
    }

    //json在线编辑器
    //https://www.bejson.com/jsoneditoronline/
    private static void mock(String url, String file) throws IOException {
        ClassPathResource resource = new ClassPathResource("mock/response/" + file );
        String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");
        stubFor(get(urlPathEqualTo(url))
                .withHeader("Content-Type", equalToIgnoreCase("application/json"))
                .willReturn(aResponse()
                .withBody(content)
                .withStatus(200)));
    }
}
