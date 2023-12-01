package chinchulin.varano.Security.Service;

import chinchulin.varano.Security.Models.User;
import chinchulin.varano.Security.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> getUsername(String username){
        return userRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public List<User> getAllUsers () {

        return userRepository.findAll();
    }

}
