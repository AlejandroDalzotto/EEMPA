package chinchulin.varano.Security.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import chinchulin.varano.Security.DTO.JwtDTO;
import chinchulin.varano.Security.DTO.LoginUser;
import chinchulin.varano.Security.DTO.NewUser;
import chinchulin.varano.Security.JWT.JwtService;
import chinchulin.varano.Security.Models.Rol;
import chinchulin.varano.Security.Models.User;
import chinchulin.varano.Security.Utils.RolName;
import jakarta.validation.Valid;

@Service
public class AuthService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtService jwtService;


    public User NewUser(NewUser newUser) {

        // convert to userDAO (db) + password encrypted
        User user = new User(newUser.getName(), newUser.getLastname(), newUser.getEmail(),
                newUser.getUsername(),
                passwordEncoder.encode(newUser.getPassword()));


        // set roles, predetermined roles -> "user", and if contains "admin", push rol admin to the user.
        Set<Rol> roles = new HashSet<>();

        roles.add(rolService.getByRolName(RolName.ROLE_USER).get());
        if (newUser.getRoles().contains("admin")) {
            roles.add(rolService.getByRolName(RolName.ROLE_ADMIN).get());
        }

        // roles add to user
        user.setRoles(roles);
        userService.saveUser(user);
        return user;
    }


    public JwtDTO login(@Valid @RequestBody LoginUser loginUser) {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
    }


}
