package demo.service;

import demo.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageService {
    Message save(Message message);

    Page<Message> getAll(Pageable pageRequest, List<String> tags);
}
