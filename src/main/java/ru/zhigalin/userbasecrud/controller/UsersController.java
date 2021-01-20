package ru.zhigalin.userbasecrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import ru.zhigalin.userbasecrud.model.User;
import ru.zhigalin.userbasecrud.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String allUsers(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "users/users";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "users/user";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/new";
        }
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }
        userService.edit(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        userService.delete(userService.getById(id));
        return "redirect:/users";
    }
}
