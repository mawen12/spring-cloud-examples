package com.mawen;

import com.mawen.ribbon.rule.TrafficRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="mailto:1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/19
 */
@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(TrafficRule.class)
public class NacosRibbonEnhanceConsumer {

    public static void main(String[] args) {
        SpringApplication.run(NacosRibbonEnhanceConsumer.class, args);
    }

    @RestController
    class HelloController {

        @Autowired
        private RestTemplate restTemplate;

        @Autowired
        private EchoService echoService;

        private String serviceName = "nacos-traffic-service";

        @GetMapping("/echo")
        public String echo(HttpServletRequest request) {
            HttpHeaders headers = new HttpHeaders();
            if (!StringUtils.isEmpty(request.getHeader("Gray"))) {
                headers.add("Gray", request.getHeader("Gray"));
            }
            HttpEntity<String> entity = new HttpEntity<>(headers);
            return restTemplate.exchange("http://" + serviceName + "/", HttpMethod.GET, entity, String.class).getBody();
        }

        @GetMapping("/echoFeign")
        public String echoFeign(HttpServletRequest request) {
            if (!StringUtils.isEmpty(request.getHeader("Gray"))) {
                return echoService.echo(request.getHeader("Gray"));
            }
            return echoService.echo("false");
        }
    }

    @FeignClient(value = "nacos-traffic-service")
    interface EchoService {

        @GetMapping("/")
        String echo(@RequestHeader("Gray") String gray);
    }

}
