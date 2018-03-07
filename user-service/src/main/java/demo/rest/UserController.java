package demo.rest;


import demo.dao.UserRepository;
import demo.exception.ApiError;
import demo.exception.RequestException;
import demo.model.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Anton Taranukha
 */
@Api(value = "User data operations", description = "RESTful API to interact with users data.")
@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    public static final String INFO_ALREADY_EXISTS = "User with the specified name already exists";

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "Get user", httpMethod = "GET", response = User.class, tags = {"User internal services"},
            notes = "Returns User with the specified username.")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "There is no user found.")})
    @RequestMapping(method = RequestMethod.GET, value = "/{login}")
    public ResponseEntity getUser(@PathVariable("login") String login) {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(user);
        }
    }


    @ApiOperation(value = "Save User", httpMethod = "POST", response = User.class, notes = "Saves new User.")
    @ApiResponses(value = {@ApiResponse(code = 422, message = INFO_ALREADY_EXISTS, response = ApiError.class)})
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity saveUser(@ApiParam(value = "New user object.", required = true) @RequestBody User user) {

        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new RequestException(HttpStatus.UNPROCESSABLE_ENTITY, INFO_ALREADY_EXISTS);
        }

        return ResponseEntity.ok().body(userRepository.save(user));
    }


}
