package com.example.demo.security;

import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Userエンティティ
 */
public class SimpleUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    SimpleUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) {
        // emailでデータベースからユーザーエンティティを検索する
        return userRepository.findByEmail(email)
                .map(SimpleLoginUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

}
