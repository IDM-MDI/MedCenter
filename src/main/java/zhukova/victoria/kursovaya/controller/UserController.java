package zhukova.victoria.kursovaya.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zhukova.victoria.kursovaya.model.dto.DiseaseDto;
import zhukova.victoria.kursovaya.service.DiseaseService;
import zhukova.victoria.kursovaya.service.UserService;

@Controller
@RequestMapping("/admin/users")
public class UserController {
    private final UserService service;
    private final DiseaseService diseaseService;

    @Autowired
    public UserController(UserService service, DiseaseService diseaseService) {
        this.service = service;
        this.diseaseService = diseaseService;
    }

    @GetMapping()
    public String getAllUsers(@RequestParam(defaultValue = "0") Integer page,Model model) {
        model.addAttribute("allUsers",service.findAllDtoByPage(page));
        model.addAttribute("nextPage", ++page);
        model.addAttribute("prevPage", --page);
        return "users";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable int id,Model model) {
        model.addAttribute("userById", service.findDtoById(id));
        model.addAttribute("emptyDisease",diseaseService.getEmpty());
        return "user";
    }
    @PostMapping("/{id}/disease")
    public String addDisease(@PathVariable int id,@ModelAttribute("diseaseById") DiseaseDto disease) {
        service.addDisease(id,disease);
        return "redirect:/admin/users";
    }


}
