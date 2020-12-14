package io.batch.schdul.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class JobController {

    @GetMapping("/job/new")
    public String addJob(Model model) {
        model.addAttribute("jobForm", new JobForm());
        return "job/createJobForm";
    }
}
