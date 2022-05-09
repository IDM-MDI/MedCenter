package zhukova.victoria.kursovaya.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    @GetMapping()
    public String getAllDoctors() {
        return "doctors";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id) {
        return "doctors";
    }

    @PatchMapping("/{id}")
    public String updateUser(@PathVariable long id) {
        return "doctors";
    }

    @GetMapping("/{id}")
    public String getDoctorById(@PathVariable long id) {
        return "doctors";
    }

}
