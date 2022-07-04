package zhukova.victoria.kursovaya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zhukova.victoria.kursovaya.model.dto.UserInfoDto;
import zhukova.victoria.kursovaya.service.DoctorService;
import zhukova.victoria.kursovaya.service.UserService;

@Controller
@RequestMapping("/menu")
public class MenuController {
    private final UserService service;
    private final DoctorService doctorService;

    @Autowired
    public MenuController(UserService service, DoctorService doctorService) {
        this.service = service;
        this.doctorService = doctorService;
    }

    @GetMapping
    public String menu(Model model) {
        model.addAttribute("isAdmin",service.isAdmin(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()));
        return "menu";
    }

    @GetMapping("/history")
    public String history(Model model) {
        model.addAttribute("userDiseases",service
                        .findUserDtoByEmail(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName())
                        .getDiseaseBook());
        return "medical-history";
    }

    @GetMapping("/centers")
    public String center(@RequestParam(defaultValue = "0") Integer page, Model model) {
        model.addAttribute("doctors",doctorService.findByPage(page));
        model.addAttribute("nextPage", ++page);
        model.addAttribute("prevPage", --page);
        return "medical-institutions";
    }

    @GetMapping("/person")
    public String person(Model model) {
        UserInfoDto info = service.findInfoByEmail(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());
        model.addAttribute("person",info);
        return "personal-inf";
    }

    @PostMapping("/person")
    public String addInfo(@ModelAttribute("person")UserInfoDto info) {
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        service.addUserInfo(email,info);
        return "redirect:/menu";
    }

}
