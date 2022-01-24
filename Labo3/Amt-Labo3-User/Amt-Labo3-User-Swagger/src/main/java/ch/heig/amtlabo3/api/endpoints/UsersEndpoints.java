package ch.heig.amtlabo3.api.endpoints;

        import ch.heig.amtlabo3.Entities.UserEntity;
        import ch.heig.amtlabo3.api.UsersApi;
        import ch.heig.amtlabo3.api.exceptions.UserNotFoundException;
        import ch.heig.amtlabo3.api.model.User;
        import ch.heig.amtlabo3.api.model.UserSummary;
        import ch.heig.amtlabo3.repositories.UserRepository;
        import io.swagger.v3.oas.annotations.security.SecurityRequirement;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RestController;
        import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

        import java.net.URI;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Optional;


@RestController
@SecurityRequirement(name= "bearerAuth")
public class UsersEndpoints implements UsersApi {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<List<User>> listUsersUsingGET()
    {
        List<UserEntity> userEntities= userRepository.findAll();
        List<User> users  = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            User user = new User();
            user.setId(userEntity.getId());
            user.setUserName(userEntity.getUserName());
            user.setUserPassword(userEntity.getUserPassword());
            user.setSolde(userEntity.getSolde());
            user.setIsAdmin(userEntity.getIsAdmin());
            user.setIsBlocked(userEntity.getIsBlocked());
            users.add(user);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    public ResponseEntity<Void> addUserUsingPOST(@RequestBody User user) {
        UserEntity userE = new UserEntity();
        userE.setId(user.getId());
        userE.setUserName(user.getUserName());
        userE.setUserPassword(user.getUserPassword());
        userE.setSolde(user.getSolde());
        userE.setIsAdmin(user.getIsAdmin());
        userE.setIsBlocked(user.getIsBlocked());

        UserEntity userAdded = userRepository.save(userE);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userAdded.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<User> listUserUsingGET(Integer id) {
        Optional<UserEntity> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            UserEntity userEntity = opt.get();
            User user = new User();
            user.setId(userEntity.getId());
            user.setUserName(userEntity.getUserName());
            user.setUserPassword(userEntity.getUserPassword());
            user.setSolde(userEntity.getSolde());
            user.setIsAdmin(userEntity.getIsAdmin());
            user.setIsBlocked(userEntity.getIsBlocked());
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } else {
//            return ResponseEntity.notFound().build();
            throw new UserNotFoundException(id);
        }
    }

    public ResponseEntity<User> deleteUserUsingDelete(Integer id) {
        Optional<UserEntity> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            userRepository.deleteById(id);
            UserEntity userEntity = opt.get();
            User user = new User();
            user.setId(userEntity.getId());
            user.setUserName(userEntity.getUserName());
            user.setUserPassword(userEntity.getUserPassword());
            user.setSolde(userEntity.getSolde());
            user.setIsAdmin(userEntity.getIsAdmin());
            user.setIsBlocked(userEntity.getIsBlocked());
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } else {
//          return ResponseEntity.notFound().build();
            throw new UserNotFoundException(id);
        }
    }

    public ResponseEntity<UserSummary> updateUserUsingPut(Integer id,@RequestBody UserSummary userSum) {
        Optional<UserEntity> opt = userRepository.findById(id);
        if (opt.isPresent()) {

            UserSummary userS = new UserSummary();
            userS.setSolde(userSum.getSolde());
            userS.setIsBlocked(userSum.getIsBlocked());

            UserEntity userE = opt.get();
            userE.setSolde(userSum.getSolde());
            userE.setIsBlocked(userSum.getIsBlocked());

            userRepository.save(userE);

            return new ResponseEntity<UserSummary>(userS, HttpStatus.OK);
        } else {
            throw new UserNotFoundException(id);
        }
    }

}