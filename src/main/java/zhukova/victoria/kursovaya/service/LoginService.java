package zhukova.victoria.kursovaya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import zhukova.victoria.kursovaya.model.dto.AuthenticationDto;

import javax.servlet.http.HttpSession;

@Service
public class LoginService {
    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void authenticate(AuthenticationDto authenticationDto, HttpSession session) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDto.getEmail(), authenticationDto.getPassword()));
        setUserSession(session,authenticationDto.getEmail());
    }
    private void setUserSession(HttpSession session, String email) {
        session.setAttribute("user", email);
    }
}
