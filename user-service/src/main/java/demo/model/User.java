package demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @author Anton Taranukha
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {
    private String id;
    private String name;
    private String login;
    private String password;
}
