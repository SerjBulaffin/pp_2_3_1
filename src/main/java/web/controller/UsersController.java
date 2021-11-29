package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserServiceImpl;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@Transactional
public class UsersController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UsersController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/users")
    public String usersTable(Model model) {
        model.addAttribute("usersList", userServiceImpl.getAllUsers());
        return "users";
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userServiceImpl.getUser(id));

        return "show_user";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("newUser") User user) {
        return "new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("newUser") User user) {
        userServiceImpl.addUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/users/{id}")
    public String deletUser(@PathVariable("id") int id) {
        userServiceImpl.removeUserById(id);
        return "users";
    }


    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("editUser", userServiceImpl.getUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
            userServiceImpl.updateUser(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userServiceImpl.removeUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/test")
    public String testPage(Model model) {
        List<String> list = new ArrayList<>();
        list.add("Privet");
        list.add("Poka");
        list.add("12345");
        list.add("GlavRiba");
        list.add("Volchiha");
        model.addAttribute("strings", list);
        return "test";
    }
}
