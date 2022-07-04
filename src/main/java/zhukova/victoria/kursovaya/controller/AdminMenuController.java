package zhukova.victoria.kursovaya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zhukova.victoria.kursovaya.model.dto.*;
import zhukova.victoria.kursovaya.service.*;

@Controller
@RequestMapping("/admin")
public class AdminMenuController {

    private final DoctorService doctorService;
    private final DiseaseCategoryService diseaseCategoryService;
    private final CityService cityService;
    private final DoctorPositionService doctorPositionService;
    private final MedCenterService medCenterService;

    @Autowired
    public AdminMenuController(DoctorService doctorService,
                               DiseaseCategoryService diseaseCategoryService, CityService cityService, DoctorPositionService doctorPositionService, MedCenterService medCenterService) {
        this.doctorService = doctorService;
        this.diseaseCategoryService = diseaseCategoryService;
        this.cityService = cityService;
        this.doctorPositionService = doctorPositionService;
        this.medCenterService = medCenterService;
    }


    @GetMapping()
    public String getAdminMenu() {
        return "admin-menu";
    }

    @GetMapping("/info")
    public String person(Model model) {
        DoctorInfoDto info = doctorService.findInfoByEmail(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());
        model.addAttribute("doctorInfo",info);
        return "doctor-personal-info";
    }
    @GetMapping("/category")
    public String category(@RequestParam(defaultValue = "0") Integer page,Model model) {
        model.addAttribute("categories",diseaseCategoryService.findAllDtoByPage(page));
        model.addAttribute("nextPage", ++page);
        model.addAttribute("prevPage", --page);
        model.addAttribute("category",diseaseCategoryService.createEmpty());
        return "addCategory";
    }

    @GetMapping("/city")
    public String getCity(@RequestParam(defaultValue = "0") Integer page,Model model) {
        model.addAttribute("cities",cityService.findAllDtoByPage(page));
        model.addAttribute("nextPage", ++page);
        model.addAttribute("prevPage", --page);
        model.addAttribute("city",cityService.getEmpty());
        return "addCity";
    }
    @GetMapping("/position")
    public String getPosition(@RequestParam(defaultValue = "0") Integer page,Model model) {
        model.addAttribute("positions",doctorPositionService.findAllDtoByPage(page));
        model.addAttribute("nextPage", ++page);
        model.addAttribute("prevPage", --page);
        model.addAttribute("position",doctorPositionService.getEmpty());
        return "addPosition";
    }

    @GetMapping("/center")
    public String getCenter(@RequestParam(defaultValue = "0") Integer page,Model model) {
        model.addAttribute("centers",medCenterService.findAllDtoByPage(page));
        model.addAttribute("nextPage", ++page);
        model.addAttribute("prevPage", --page);
        model.addAttribute("center",medCenterService.getEmpty());
        return "addMedCenter";
    }

    @PostMapping
    public String addAdmin(@ModelAttribute("adminAccount")DoctorDto admin) {
        doctorService.saveDoctor(admin);
        return "redirect:/admin";
    }

    @PostMapping("/center")
    public String addCenter(@ModelAttribute("center")MedCenterDto center) {
        medCenterService.addCenter(center);
        return "redirect:/admin";
    }

    @PostMapping("/info")
    public String addInfo(@ModelAttribute("doctorInfo")DoctorInfoDto info) {
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        doctorService.addDoctorInfo(email,info);
        return "redirect:/admin";
    }
    @PostMapping("/category")
    public String addCategory(@ModelAttribute("category") DiseaseCategoryDto category) {
        diseaseCategoryService.addCategory(category);
        return "redirect:/admin";
    }
    @GetMapping("/add")
    public String admin(Model model) {
        model.addAttribute("adminAccount",doctorService.getEmptyDoctor());
        return "addAdmin";
    }

    @PostMapping("/city")
    public String addCity(@ModelAttribute("city") CityDto city) {
        cityService.addCity(city);
        return "redirect:/admin";
    }
    @PostMapping("/position")
    public String addPosition(@ModelAttribute("position") DoctorPositionDto positionDto) {
        doctorPositionService.addPosition(positionDto);
        return "redirect:/admin";
    }
}
