package ru.ulstu.is.sbapp.user.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ulstu.is.sbapp.user.dto.UserSignUpDto;
import ru.ulstu.is.sbapp.user.enums.UserRole;
import ru.ulstu.is.sbapp.user.model.User;
import ru.ulstu.is.sbapp.user.service.UserService;

import javax.validation.Valid;
import javax.validation.ValidationException;

@Controller
@RequestMapping(UserSignUpMvcController.SIGNUP_URL)
public class UserSignUpMvcController {
    public static final String SIGNUP_URL = "/signup";

    private final UserService userService;
    private UserRole userRole;

    public UserSignUpMvcController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showSignupForm(Model model) {
        model.addAttribute("userDto", new UserSignUpDto());
        return "signup";
    }

    @PostMapping
    public String signup(@ModelAttribute("userDto") @Valid UserSignUpDto userSignupDto,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "signup";
        }
        try {
            final User user = userService.createUser(
                    userSignupDto.getLogin(), userSignupDto.getPassword(),
                    userSignupDto.getPasswordConfirm(), userRole.valueOf(userSignupDto.getUserRole()));
            return "redirect:/login?created=" + user.getLogin();
        } catch (ValidationException | IllegalArgumentException e) {
            model.addAttribute("errors", e.getMessage());
            return "signup";
        }
    }
}
