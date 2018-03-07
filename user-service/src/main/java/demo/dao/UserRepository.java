package demo.dao;


import demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Anton Taranukha
 */
public interface UserRepository extends MongoRepository<User, String> {
    User findByLogin(String login);
}
