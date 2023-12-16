package chinchulin.varano.Security.JWT;

import java.io.IOException;

import chinchulin.varano.Security.Service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    JwtService jwtService;

    @Autowired
    UserDetailsServiceImpl detailsServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {

        try {
            //1. in the request, get token in with the method 'GetToken' of the header.
            String token = getToken(req);
            System.out.println("token value: "+ token);

            // 2.token validation. ¿Does the token exist and is it well-formed?
            if(token != null && jwtService.validateToken(token)){

                //3 get username from payload in the jwt
                String username = jwtService.getUsernameFromToken(token);

                //4 get PrincipalUser, not user(DAO) in the payload from jwt (this method 'loadUserByUsername' return UserDetails why Entity User implement UserDetails interface
                UserDetails userDetails = detailsServiceImpl.loadUserByUsername(username);

                /*5.
                *this class implement an interface that interests us.
                * class -> UsernamePasswordAuthenticationToken implement AbstractAuthenticationToken
                * class -> AbstractAuthenticationToken implement Authentication and CredentialsContainer
                * Authentication is the interface that interests us, with that we can access
                * Spring Security's SecurityContextHolder
                 */
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                //6. Set the object Authentication the SecurityContextHolder (Spring Security)
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e){
            logger.error("fail en el metodo doFilter " + e.getMessage());
        }
        filterChain.doFilter(req, res);
    }

    private String getToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer"))
            return header.replace("Bearer ", "");
        return null;
    }

}