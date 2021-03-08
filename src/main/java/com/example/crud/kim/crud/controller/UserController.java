package com.example.crud.kim.crud.controller;

import com.example.crud.kim.crud.model.Role;
import com.example.crud.kim.crud.model.User;
import com.example.crud.kim.crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/user")
    public String readUser(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        //model.addAttribute("principal", principal);
        return "user";
    }

    @GetMapping("/admin/users")
    public String reaAll(Model model) {
        model.addAttribute("users", userService.readAll());
        return "/admin/users";
    }

    @GetMapping("/admin/newUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }

    @PostMapping("/admin/newUser")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam(value = "roles") String[] role) {
        Set<Role> listRole = new HashSet<>();
        for (int i = 0; i < role.length; i++) {
            listRole.add(userService.getRoleByName(role[i]));
        }
        user.setRoles(listRole);
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/update/{id}")
    public String updateUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.readUser(id));
        return "/admin/update";
    }

    @PatchMapping("/admin/update/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam(value = "roles") String[] role, @PathVariable("id") Long id) {
        Set<Role> listRole = new HashSet<>();
        for (int i = 0; i < role.length; i++) {
            listRole.add(userService.getRoleByName(role[i]));
        }
        user.setRoles(listRole);
        userService.update(user, id);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/admin/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
