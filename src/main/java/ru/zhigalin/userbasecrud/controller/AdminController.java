package ru.zhigalin.userbasecrud.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zhigalin.userbasecrud.model.User;
import ru.zhigalin.userbasecrud.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "users";
    }

    @GetMapping("/users/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "addUser";
    }

    @PostMapping()
    public String add(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "editUser";
    }

    @PatchMapping("/users/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.edit(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(userService.getById(id));
        return "redirect:/admin/users";
    }
}
