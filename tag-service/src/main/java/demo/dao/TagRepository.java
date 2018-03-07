package demo.dao;

import demo.model.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagRepository extends MongoRepository<Tag, String> {
    Tag findByText(String text);
}
