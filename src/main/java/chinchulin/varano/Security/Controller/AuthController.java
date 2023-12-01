package chinchulin.varano.Security.Controller;

import chinchulin.varano.Security.DTO.JwtDTO;
import chinchulin.varano.Security.DTO.LoginUser;
import chinchulin.varano.Security.DTO.NewUser;
import chinchulin.varano.Security.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;


    @PostMapping("/new")
    public ResponseEntity<?> CreateUser (@Valid @RequestBody NewUser newUser) {

        authService.nuevo(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    // LOGIN usuario
    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login (@Valid @RequestBody LoginUser loginUser) {

        JwtDTO jwtDto = authService.login(loginUser);

        return ResponseEntity.status(HttpStatus.OK).body(jwtDto);
    }

}
