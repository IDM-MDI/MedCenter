package zhukova.victoria.kursovaya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zhukova.victoria.kursovaya.service.DoctorService;

@Controller
@RequestMapping("/menu/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public String getAllDoctors(@RequestParam(defaultValue = "0") Integer page, Model model) {
        model.addAttribute("doctors",doctorService.findByPage(page));
        model.addAttribute("nextPage", ++page);
        model.addAttribute("prevPage", --page);
        return "doctors";
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        return "doctors";
    }

    @PatchMapping("/{id}")
    public String updateUser(@PathVariable int id) {
        return "doctors";
    }

    @GetMapping("/{id}")
    public String getDoctorById(@PathVariable int id,Model model) {
        model.addAttribute("doctorById", doctorService.findDtoById(id));
        return "doctor";
    }
}
