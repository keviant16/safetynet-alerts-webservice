package com.simplon.safetynetalertswebservice.security.service;

import com.simplon.safetynetalertswebservice.model.entity.User;
import com.simplon.safetynetalertswebservice.repository.UserRepository;
import com.simplon.safetynetalertswebservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class IUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        return UserDetailsImpl.build(user);
    }

}
