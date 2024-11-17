package com.nhom1.deep_sleeping.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainController {

    @GetMapping(value = {"/", "/home"})
    String home() {
        return "home";
    }

    @GetMapping("/lull")
    String lull() {
        return "lull";
    }

    @GetMapping("/metho")
    String metho() {
        return "metho";
    }

    @GetMapping("/timer")
    String timer() {
        return "timer";
    }

}
