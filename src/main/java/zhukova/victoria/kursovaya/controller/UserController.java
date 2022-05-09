package zhukova.victoria.kursovaya.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zhukova.victoria.kursovaya.model.entity.User;
import zhukova.victoria.kursovaya.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public String getAllUsers() {
        return "users";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable long id) {
        return "users";
    }

    @PostMapping
    public String registration(@ModelAttribute("registrationUser") User user,Model model) {
        service.addUser(user);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginUser")User user, HttpSession session) {
        boolean isLogin = service.login(user);
        if(isLogin) {
            session.setAttribute("user",user);
            return "redirect:/menu";
        }
        else {
            return "redirect:/login";
        }
    }
}
