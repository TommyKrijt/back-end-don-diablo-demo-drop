package nl.novi.krijt.service;

import nl.novi.krijt.domain.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(long id);

    void deleteUser(long id);

    long saveUser(User user);

    void updateUser(long id, User user);

    ResponseEntity<?> findUserByToken(String token);
}

