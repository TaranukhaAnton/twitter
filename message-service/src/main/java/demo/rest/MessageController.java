package demo.rest;

import demo.model.Message;
import demo.service.MessageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {
    private static final String DEFAULT_PAGE_SIZE = "5";
    private static final String DEFAULT_PAGE_NUMBER = "0";

    @Autowired
    MessageService messageService;

    @ApiOperation(value = "Get messages", httpMethod = "GET", response = Page.class, tags = {"Message service"}, notes = "Get messages using parameters.",
            authorizations = {@Authorization(value = "oauth2", scopes = {@AuthorizationScope(scope = "read", description = "read data")})})
    @GetMapping
    public ResponseEntity list(
            @ApiParam(value = "Tag list for messages filtering .", required = false, defaultValue = "")
            @RequestParam(value = "tag", required = false) List<String> tags,
            @ApiParam(value = "The page number for pagination.", required = false, defaultValue = DEFAULT_PAGE_NUMBER)
            @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @ApiParam(value = "The page size for pagination.", required = false, defaultValue = DEFAULT_PAGE_SIZE)
            @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize) {

        PageRequest pageRequest = new PageRequest(page, pageSize);
        Page<Message> messages = messageService.getAll(pageRequest, tags);
        return ResponseEntity.ok().body(messages);
    }

    @ApiOperation(value = "Save message", httpMethod = "POST", response = ResponseEntity.class,
            notes = "Saves message.", authorizations = {@Authorization(value = "oauth2", scopes = {@AuthorizationScope(scope = "write", description = "write data")})})

    @PostMapping
    public ResponseEntity postMessages(Principal principal, @RequestBody String text) {
        Message message = new Message();
        message.setText(text);
        message.setUsername(principal.getName());
        message.setCreatedAt(LocalDateTime.now());
        messageService.save(message);
        return ResponseEntity.ok().body(message);
    }


}
