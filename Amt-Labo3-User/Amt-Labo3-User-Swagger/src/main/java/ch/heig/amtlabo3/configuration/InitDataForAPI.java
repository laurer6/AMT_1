package ch.heig.amtlabo3.configuration;

import ch.heig.amtlabo3.Entities.UserEntity;
import ch.heig.amtlabo3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Configuration
public class InitDataForAPI {

    @Autowired
    private UserRepository userRepository;

    public void generateDataForUsers(){

        userRepository.saveAll(List.of(
                new UserEntity("admin","admin", BigDecimal.valueOf(100),false,true),
                new UserEntity("Jean","1234", BigDecimal.valueOf(100),false,true),
                new UserEntity("Lisa","abcd", BigDecimal.valueOf(100),false,true),
                new UserEntity("Mona","password", BigDecimal.valueOf(100),false,false),
                new UserEntity("Claude","qwertz", BigDecimal.valueOf(100),false,false),
                new UserEntity("Marc","0000", BigDecimal.valueOf(100),false,false)
        ));

    }
}

