package com.retail.ordering.system.orderservice.configuration.security;

import com.retail.ordering.system.orderservice.entities.User;
import com.retail.ordering.system.orderservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userName));

        return UserPrincipal.build(user);
    }

}