package zhukova.victoria.kursovaya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zhukova.victoria.kursovaya.model.dto.AuthenticationDto;
import zhukova.victoria.kursovaya.service.UserService;

@Controller
@RequestMapping("/")
public class IndexController {

    private final UserService service;

    @Autowired
    public IndexController(UserService service) {
        this.service = service;
    }

    @GetMapping(value = {"/","/login"})
    public String getAuthorization(Model model) {
        model.addAttribute("loginUser",new AuthenticationDto());
        return "authorization";
    }


    @GetMapping("/registration")
    public String getRegistration(Model model) {
        model.addAttribute("registrationUser",new AuthenticationDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("registrationUser") AuthenticationDto dto) throws Exception {
        service.addUser(dto);
        return "redirect:/login";
    }
}
