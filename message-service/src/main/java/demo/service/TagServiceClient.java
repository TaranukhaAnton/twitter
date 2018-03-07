package demo.service;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("tag-service")
public interface TagServiceClient {
    @PostMapping(path = "/api/v1/internal/tag/")
    ResponseEntity saveTags(@RequestBody List<String> tags);
}
