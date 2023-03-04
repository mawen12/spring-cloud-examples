package com.mawen.gateway.zuul.service;

import com.mawen.gateway.zuul.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/4
 */
@Slf4j
@Service
public class AggregationService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<User> getUserById(Long id) {
        // 创建一个观察者
        return Observable.create(observer -> {
           // 请求用户微服务的/{id}端点
            User user = restTemplate.getForObject("http://microservice-provider-user/{id}", User.class, id);
            log.info("send request to http://microservice-provider-user/{}, result: {}.", id, user);
            observer.onNext(user);
            observer.onCompleted();
        });
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<User> getMovieUserByUserId(Long id) {
        return Observable.create(observer -> {
            // 请求电影微服务的/user/{id}端点
            User movieUser = restTemplate.getForObject("http://microservice-consumer-movie/user/{id}", User.class, id);
            log.info("send request to http://microservice-consumer-movie/user/{}, result: {}.", id, movieUser);
            observer.onNext(movieUser);
            observer.onCompleted();
        });
    }

    public User fallback(Long id) {
        User user = new User();
        user.setId(-1L);
        return user;
    }

}
