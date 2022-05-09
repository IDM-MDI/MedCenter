package zhukova.victoria.kursovaya.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zhukova.victoria.kursovaya.model.entity.Address;
import zhukova.victoria.kursovaya.model.entity.User;
import zhukova.victoria.kursovaya.model.entity.UserInfo;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping(value = {"/","/login"})
    public String getAuthorization(Model model) {
        model.addAttribute("loginUser",new User());
        return "authorization";
    }


    @GetMapping("/registration")
    public String getRegistration(Model model) {
        model.addAttribute("registrationUser",new User());
        return "registration";
    }


    @GetMapping("/menu")
    public String menu(HttpSession session) {
        User user = (User)session.getAttribute("user");
        System.out.println(user.getEmail());
        return "menu";
    }



    @GetMapping("/history")
    public String history() {
        return "medical-history";
    }

    @GetMapping("/centers")
    public String center() {
        return "medical-institutions";
    }

}
