package demo.service;

import demo.model.Tag;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TagService {
    void save(List<String> tags);

    Page<Tag> getPopularTags();

}
