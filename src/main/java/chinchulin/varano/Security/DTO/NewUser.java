package chinchulin.varano.Security.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUser {

        //use this object for get all personal data  the users. (DTO)
        @NotBlank
        private String name;

        @NotBlank
        private String lastname;

        @Email
        private String email;

        @NotBlank
        private String username;

        @NotBlank
        private String password;

        private Set<String> roles = new HashSet<>();

}
