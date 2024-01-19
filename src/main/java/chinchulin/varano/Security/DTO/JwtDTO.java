package chinchulin.varano.Security.DTO;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtDTO {

        private String token;
        private String username;
        private Collection<? extends GrantedAuthority> authorities;

        public JwtDTO(String token, String username, Collection<? extends GrantedAuthority> authorities) {
                this.token = token;
                this.username = username;
                this.authorities = authorities;
        }
}
