package zhukova.victoria.kursovaya.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zhukova.victoria.kursovaya.model.entity.Address;
import zhukova.victoria.kursovaya.model.entity.City;
import zhukova.victoria.kursovaya.model.entity.User;
import zhukova.victoria.kursovaya.model.entity.UserInfo;
import zhukova.victoria.kursovaya.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/person")
public class PersonController {

    private final UserService service;

    @Autowired
    public PersonController(UserService service) {
        this.service = service;
    }


    @GetMapping()
    public String person(HttpSession session,Model model) {
        User user = (User)session.getAttribute("user");
        UserInfo info = null;
        if(user.getInfo() == null) {
            info = new UserInfo();
            Address address = new Address();
            address.setCity(new City());
            info.setAddress(address);
        }
        else {
            info = user.getInfo();
        }
        model.addAttribute("person",info);
        return "personal-inf";
    }

    @PostMapping
    public String addInfo(@ModelAttribute("person")UserInfo info, HttpSession session) {
        User user = (User)session.getAttribute("user");
        service.addUserInfo(user,info);
        return "redirect:/menu";
    }
}
