package com.testkafka.springkafka.user;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RequestMapping("/api/users")
@RestController
public class  UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping()
    Collection<User> getAllUsers(@RequestParam(name = "search") Optional<String> search) {

        var seachString = search.orElse("");

        log.info("searching for {}", seachString);

        return List.of(
            new User("1", "John"),
            new User("2", "Jane")
        );
    }
}

sealed interface UserEvent permits UserCreatedEvent, UserUpdatedEvent, UserCreated {
    User updatedUser(User user);
}

@Slf4j
record UserUpdatedEvent(String id, String name) implements UserEvent {

    @Override
    public User updatedUser(User user) {

        return user;
    }
}

@Slf4j
record UserCreatedEvent(String id, String name) implements UserEvent {
    @Override
    public User updatedUser(User user) {

        return null;
    }
}

final class UserCreated  implements UserEvent  {

    @Override
    public User updatedUser(User user) {
        return null;
    }
}

record User(String id, String name) {}
