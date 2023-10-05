package lab.microservice.greet;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-service")
@LoadBalancerClient(name = "book-service", configuration = LoadBalancerConfig.class)
@EnableDiscoveryClient
public interface BookServiceProxy {
    @GetMapping("/books/{id}")
    public BookDTO getBook(@PathVariable Long id);
}
