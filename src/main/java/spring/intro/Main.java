package spring.intro;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.intro.config.AppConfig;
import spring.intro.dao.impl.UserDaoImpl;
import spring.intro.model.User;
import spring.intro.service.UserService;

public class Main {
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        userService.add(new User("mail@mail.com", "Boris"));
        userService.add(new User("peter@mail.com", "Peter"));
        userService.add(new User("alice@mail.com", "Alice"));

        logger.info(userService.listUsers());
    }
}
