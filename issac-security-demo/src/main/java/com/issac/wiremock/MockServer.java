package com.issac.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * author:  ywy
 * date:    2019-01-24
 * desc:
 */
public class MockServer {

//    public static void main(String[] args) throws IOException {
//        WireMock.configureFor("localhost",8062);
//        WireMock.removeAllMappings();
//        mock("/order/1","01");
//
//    }
//
//    private static void mock(String url,String filename) throws IOException {
//        ClassPathResource resource = new ClassPathResource("mock/response/"+filename+".txt");
//        String content = StringUtils.join(
//                org.apache.commons.io.FileUtils.readLines(resource.getFile(),
//                        "UTF-8"),"");
//
//        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(url))
//                .willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
//    }
}
