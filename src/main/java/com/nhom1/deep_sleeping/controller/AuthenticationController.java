package com.nhom1.deep_sleeping.controller;

import com.nhom1.deep_sleeping.dto.request.RegisterRequest;
import com.nhom1.deep_sleeping.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @GetMapping
    String auth(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName())) {
            return "redirect:/";
        }
        model.addAttribute("registerRequest", new RegisterRequest());
        return "login";
    }

        @PostMapping("/register")
        String register(
                @Valid RegisterRequest request,
                BindingResult bindingResult,
                RedirectAttributes redirectAttributes) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName())) {
                return "redirect:/";
            }
            if (bindingResult.hasErrors()) {
                return "login";
            }

            try {
                authenticationService.register(request);
            } catch (DataIntegrityViolationException e) {
                bindingResult.rejectValue("email", "error.user.duplicate", "Email already exists");
                return "login";
            }
            redirectAttributes.addFlashAttribute("message", "Register successfully!");
            return "redirect:/auth";
        }
}
