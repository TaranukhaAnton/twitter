package demo.service;


import demo.model.CustomUserDetails;
import demo.model.User;
import demo.service.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceClient userController;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try{
             user = userController.getUser(username);

        } catch (Throwable t){
            System.out.println("t = " + t);
        }

        if (user != null) {
            return new CustomUserDetails(user.getName(), user.getPassword());
        } else {
            throw new UsernameNotFoundException("not found");
        }

    }
}