package ks.msx.SpringSecurity.service;

import ks.msx.SpringSecurity.entity.User;
import ks.msx.SpringSecurity.repository.UserRepository;
import ks.msx.SpringSecurity.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        var u = userRepository.findUserByUsername(username);
        return u.map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User with this username not found " + username));
    }

    public void createUser(User user){
        userRepository.save(user);
    }
}
