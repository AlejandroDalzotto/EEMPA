package chinchulin.varano.Security.Service;

import chinchulin.varano.Security.Models.PrincipalUser;
import chinchulin.varano.Security.Models.User;
import chinchulin.varano.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUsername(username).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        return PrincipalUser.build(user);
    }

}
