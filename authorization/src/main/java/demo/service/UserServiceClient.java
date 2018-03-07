package demo.service;


import demo.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserServiceClient {
    @GetMapping(path = "api/v1/user/{login}")
    User getUser(@PathVariable("login") String login);
}
