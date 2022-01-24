package ch.heig.amt.services;

import ch.heig.amt.util.UserInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CheckTokenService {

    public UserInfo getUserInfoFromToken(String token) {
        return new UserInfo("lionel", new BigDecimal(10.0), "ouvert");
    }
}
