package zhukova.victoria.kursovaya.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhukova.victoria.kursovaya.model.dto.SecurityUser;
import zhukova.victoria.kursovaya.service.AuthenticationService;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final AuthenticationService service;


    @Autowired
    public CustomUserDetailsService(AuthenticationService service) {
        this.service = service;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SecurityUser user = service.findUserByEmail(email);
        CustomUserDetails userDetails = CustomUserDetails.getUserDetails(user);
        log.info("SecurityUser with email: {} successfully loaded",email);
        return userDetails;
    }
}
