package ru.karelin.tmspringws.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.karelin.tmspringws.entity.User;

public interface UserService extends UserDetailsService {


    User findByLogin(String login);

    @Override
    UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;
}
