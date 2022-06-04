package com.example.springsecuritydemo.security;

import com.example.springsecuritydemo.model.User;
import com.example.springsecuritydemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import static com.example.springsecuritydemo.security.SecurityUser.fromUser;

/**
 * Отвечает за поиск информации о пользователе, на основе которой можно осуществить авторизацию
 */
@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with login " + email + " doesn't exists"));
        return fromUser(user);
    }
}