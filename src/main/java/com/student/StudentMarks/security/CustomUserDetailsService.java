package com.student.StudentMarks.security;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

        // You can replace this with DB access later
        private static final Map<String, String> userMap = Map.of(
                "admin", "admin123",
                "user", "user123"
        );

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            String password = userMap.get(username);

            if (password == null) {
                throw new UsernameNotFoundException("User not found: " + username);
            }

            return new User(username, password, new ArrayList<>());
        }
    }


