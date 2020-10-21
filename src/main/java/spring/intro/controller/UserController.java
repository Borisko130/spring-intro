package spring.intro.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public UserResponseDto get(@PathVariable Long userId) {
        return convertUserToDto(userService.getById(userId));
    }

    @GetMapping("/")
    public List<UserResponseDto> getAll() {
        List<UserResponseDto> users = new ArrayList<>();
        for (User user : userService.listUsers()) {
            users.add(convertUserToDto(user));
        }
        return users;
    }

    private UserResponseDto convertUserToDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        return dto;
    }

}
