package com.retail.ordering.system.orderservice.runner;

import com.retail.ordering.system.orderservice.entities.Role;
import com.retail.ordering.system.orderservice.entities.User;
import com.retail.ordering.system.orderservice.properties.AppDataProperties;
import com.retail.ordering.system.orderservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AppCreationRunner implements ApplicationRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AppDataProperties appDataProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {



            log.info("inside run **********"+appDataProperties.getUsers());

            for (AppDataProperties.User newUser : appDataProperties.getUsers()) {
                User user = User.builder()
                        .userName(newUser.getUserName())
                        .password(passwordEncoder.encode(newUser.getPassword()))
                        .role(Role.ROLE_USER)
                        .build();

                if (! userRepository.existsByUserName(user.getUserName())) {
                    user = userRepository.save(user);
                    log.info("User {} created for {}...", user.getId() , newUser.getUserName());
                }
            }
        } catch (Exception ex) {
            log.error("Error running system init", ex);
            throw ex;
        }
    }

}
