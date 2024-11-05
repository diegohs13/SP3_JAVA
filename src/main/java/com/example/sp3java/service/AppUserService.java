package com.example.sp3java.service;

import com.example.sp3java.domain.AppUser;
import com.example.sp3java.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser appUser = appUserRepository.findByUsername(username);

        if (appUser != null) {
            var springUser = User.withUsername(appUser.getUsername())
                    .password(appUser.getPassword())
                    .roles("USER")
                    .build();

            return springUser;
        }

        return null;
    }
}
