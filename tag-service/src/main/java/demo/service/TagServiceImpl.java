package demo.service;

import demo.dao.TagRepository;
import demo.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    public static final int POPULAR_TAGS_SIZE = 5;
    @Autowired
    private TagRepository tagRepository;


    @Override
    public Page<Tag> getPopularTags() {
        PageRequest request = new PageRequest(0, POPULAR_TAGS_SIZE, new Sort(Sort.Direction.DESC, "count"));
        return tagRepository.findAll(request);
    }

    @Override
    public void save(List<String> tags) {
        for (String text : tags) {
            Tag tag = tagRepository.findByText(text);
            if (tag != null) {
                tag.setCount(tag.getCount() + 1);
            } else {
                tag = new Tag();
                tag.setText(text);
                tag.setCount(1);
            }
            tagRepository.save(tag);
        }

    }
}
