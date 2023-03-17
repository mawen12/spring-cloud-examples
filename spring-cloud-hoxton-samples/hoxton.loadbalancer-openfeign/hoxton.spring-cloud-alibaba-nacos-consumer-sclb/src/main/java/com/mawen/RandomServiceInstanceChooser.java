package com.mawen;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.ServiceInstanceChooser;

import java.util.List;
import java.util.Random;

/**
 * 随机算法的服务实例选择器
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/14
 */
public class RandomServiceInstanceChooser implements ServiceInstanceChooser {

    private final DiscoveryClient discoveryClient;

    private final Random random;

    public RandomServiceInstanceChooser(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
        this.random = new Random();
    }

    @Override
    public ServiceInstance choose(String serviceId) {
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances(serviceId);
        return serviceInstanceList.get(random.nextInt(serviceInstanceList.size()));
    }
}
