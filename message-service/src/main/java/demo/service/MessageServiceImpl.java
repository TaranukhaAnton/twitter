package demo.service;

import demo.dao.MessageRepository;
import demo.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private TagServiceClient tagServiceClient;

    @Override
    public Message save(Message message) {
        List<String> tags = extractHashTag(message.getText());
        message.setTags(tags);
        tagServiceClient.saveTags(tags);
        return messageRepository.save(message);
    }

    @Override
    public Page<Message> getAll(Pageable pageRequest, List<String> tags) {
        if (tags != null && !tags.isEmpty()) {
            return messageRepository.findByTagsIn(pageRequest, tags);
        } else {
            return messageRepository.findAll(pageRequest);
        }
    }


    private List<String> extractHashTag(String text) {
        Pattern MY_PATTERN = Pattern.compile("#(\\S+)");
        Matcher mat = MY_PATTERN.matcher(text);
        List<String> tags = new ArrayList<>();
        while (mat.find()) {
            tags.add(mat.group(1));
        }
        return tags;
    }

}
