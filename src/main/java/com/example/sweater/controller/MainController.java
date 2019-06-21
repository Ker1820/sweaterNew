package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Model model) {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getDetails());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName().getClass());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());

        model.addAttribute("myName", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String filter, Model model) {


        Iterable<Message> messages = messageRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.addAttribute("messages", messages);


        return "main";
}

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Message message,
            BindingResult bindingResult,
            Model model,
            @RequestParam MultipartFile file
    ) throws IOException {

        message.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("message", message);
        } else {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists())
                    uploadDir.mkdir();

                String resultFilename = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
                System.out.println(file.getOriginalFilename());
                System.out.println(resultFilename);


                file.transferTo(new File(uploadPath + "/" + resultFilename));
                message.setFilename(resultFilename);

            }

            model.addAttribute("message", null);

            messageRepo.save(message);
        }

        Iterable<Message> messages = messageRepo.findAll();

        model.addAttribute("messages", messages);

        return "main";
    }


}
