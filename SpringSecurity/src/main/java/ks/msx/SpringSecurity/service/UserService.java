package ks.msx.SpringSecurity.service;

import ks.msx.SpringSecurity.entity.Authority;
import ks.msx.SpringSecurity.entity.User;
import ks.msx.SpringSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username).orElseThrow();
    }

    public void createUser(User user){
        user.setAuthority(Authority.READ.name());
        userRepository.save(user);
    }
}
