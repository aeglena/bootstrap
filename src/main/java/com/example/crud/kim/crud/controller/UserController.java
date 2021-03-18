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
        return "user";
    }

    @GetMapping("/findOne")
    @ResponseBody
    public User findOne(Long id) {
        return userService.readUser(id);
    }

    @GetMapping("/admin/users")
    public String reaAll(Model model, Principal principal) {
        model.addAttribute("users", userService.readAll());
        User user = new User();
        user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "/admin/users";
    }

    @PostMapping("/admin/newUser")
    public String saveUser(@ModelAttribute("newUser") User user, @RequestParam(value = "roles_id") String[] role) {
        Set<Role> listRole = new HashSet<>();
        for (int i = 0; i < role.length; i++) {
            listRole.add(userService.getRoleByName(role[i]));
        }
        user.setRoles(listRole);
        userService.save(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/update")
    public String updateUser(@ModelAttribute("userUpdate") User user, @RequestParam(value = "uproles") String[] role) {
        Set<Role> listRole = new HashSet<>();
        for (int i = 0; i < role.length; i++) {
            listRole.add(userService.getRoleByName(role[i]));
        }
        user.setRoles(listRole);
        userService.update(user, user.getId());
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@ModelAttribute("userDelete") User user) {
        userService.delete(user.getId());
        return "redirect:/admin/users";
    }
}
