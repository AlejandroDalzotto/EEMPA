package chinchulin.varano.Security.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrincipalUser implements UserDetails{

    private String name;

    private String lastname;

    private String email;

    private String username;

    private String password;

    /**
     * De aqui en adelante. todo complicado de explicar
     * 1. crear una coleccion de Authoridades. (Linea 50)
     * 2. Crear un constructor con todas las propiedad (linea 25)
     * 3. Crear un metodo Build() -> (linea 52)
     * Bien. para resumir. este método lo que hace es revisar si el usuario es un administrador o un user.
     * Primera parte: crea una lista de authorities. revisara cual es el rol del usuario. y lo guarda en esa lista.
     * Segunda parte: creara un nuevo usuario principal pasando como ultimo parametro el "authorities".
     */

    private Collection<? extends GrantedAuthority> authorities;

    public static PrincipalUser build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
                .getRolName().name())).collect(Collectors.toList());

        return new PrincipalUser(user.getName(), user.getLastname(), user.getEmail(),
                user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}
