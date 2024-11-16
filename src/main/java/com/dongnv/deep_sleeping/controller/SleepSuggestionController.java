package com.dongnv.deep_sleeping.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SleepSuggestionController {

    @GetMapping("/sleep-suggestion")
    String sleepSuggestion() {
        return "sleep-suggestion";
    }
}
