package ch.heig.amtlabo3.api.util;

import ch.heig.amtlabo3.Entities.UserEntity;
import ch.heig.amtlabo3.api.models.AuthenticationRequest;
import ch.heig.amtlabo3.api.models.AuthenticationResponse;

import ch.heig.amtlabo3.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class JwtRessource {

    private String SECRET_KEY = "secret";

    int numReservation = 0;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        List<UserEntity> userEntities = userRepository.findAll();

        for (UserEntity userEntity : userEntities) {
            if(userEntity.getUserName().equals(authenticationRequest.getUsername())){
                if(userEntity.getUserPassword().equals(authenticationRequest.getPassword())) {
                    final String tokenJwt = jwtTokenUtil.getToken(authenticationRequest.getUsername());
                    return ResponseEntity.ok(new AuthenticationResponse(tokenJwt));

                    // final String tokenJwt = Jwts.builder().setSubject(authenticationRequest.getUsername()).setIssuedAt(new Date())
                    //        .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
                }
            }
        }

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/reservation", method = RequestMethod.POST)
    public ResponseEntity<?> createReservationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        List<UserEntity> userEntities = userRepository.findAll();

        for (UserEntity userEntity : userEntities) {
            if(userEntity.getUserName().equals(authenticationRequest.getUsername())){
                if(userEntity.getUserPassword().equals(authenticationRequest.getPassword())) {


                    final String tokenJwt = jwtTokenUtil.getToken(authenticationRequest.getUsername() + "/solde/" + userEntity.getSolde() + "/code/Ouvert" + "/nRservation/" + numReservation++ );
                    return ResponseEntity.ok(new AuthenticationResponse(tokenJwt));

                    // final String tokenJwt = Jwts.builder().setSubject(authenticationRequest.getUsername()).setIssuedAt(new Date())
                    //        .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
                }
            }
        }
        return ResponseEntity.notFound().build();
    }
}
