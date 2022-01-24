package ch.heig.amtlabo3.api.filters;


import ch.heig.amtlabo3.Entities.UserEntity;
import ch.heig.amtlabo3.api.models.AuthenticationResponse;
import ch.heig.amtlabo3.api.util.JwtUtil;
import ch.heig.amtlabo3.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

     @Autowired
     private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException{

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        try {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
                jwt = authorizationHeader.substring(7);
                username = jwtUtil.extractUsername(jwt);

                //List<UserEntity> userEntities = userRepository.findAll();

                if(!username.contains("ADMIN")){
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "not admin");
                    return;
                }
            }

            else{
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "bad token");
                return;
            }

            chain.doFilter(request, response);
        }catch (JwtException e){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "bad token");
            return;
        }
    }
}
