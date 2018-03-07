package demo.dao;

import demo.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    Page<Message> findAll(Pageable pageRequest);

    Page<Message> findByTagsIn(Pageable pageRequest, List<String> tags);

}
