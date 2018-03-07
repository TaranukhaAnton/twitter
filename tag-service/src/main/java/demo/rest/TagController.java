package demo.rest;


import demo.model.Tag;
import demo.service.TagService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    @ApiOperation(value = "Get popular tags", httpMethod = "GET", response = Page.class, tags = {"Tag service"},
            notes = "Returns list of popular tags.")
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/tag/popular")
    public ResponseEntity getPopular() {
        Page<Tag> popularTags = tagService.getPopularTags();
        return ResponseEntity.ok().body(popularTags);
    }


    @ApiOperation(value = "Save tag list", httpMethod = "POST", response = ResponseEntity.class, tags = {"Tag service"}, notes = "Save tag list.")
    @PostMapping(value = "/api/v1/internal/tag/", consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity saveTags(@RequestBody List<String> tags) {
        tagService.save(tags);
        return ResponseEntity.ok().body("{\"result\":\"ok\"}"); //todo
    }


}
