package spring.intro.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.intro.dto.UserResponseDto;
import spring.intro.model.User;
import spring.intro.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/inject")
    public String inject() {
        userService.add(new User("mail@mail.com", "Boris"));
        userService.add(new User("peter@mail.com", "Peter"));
        userService.add(new User("alice@mail.com", "Alice"));
        userService.add(new User("donny@mail.com", "Donald"));
        return "Users were successfully injected";
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(Long userId) {
        return null;
    }


    @GetMapping("/")
    public List<UserResponseDto> getAll() {
        List<UserResponseDto> users = new ArrayList<>();
        for (User user : userService.listUsers()) {
            users.add(userToDto(user));
        }
        return users;
    }

    public UserResponseDto userToDto(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail());
    }

}
