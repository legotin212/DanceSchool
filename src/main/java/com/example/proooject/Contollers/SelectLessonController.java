package com.example.proooject.Contollers;

import com.example.proooject.Model.User;
import com.example.proooject.Repository.UserRepository;
import com.example.proooject.Repository.LessonRepository;
import com.example.proooject.Service.LessonService;
import com.example.proooject.Service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.AuthProvider;

@Controller
@Slf4j
@RequestMapping("/pc/selectLesson")
public class SelectLessonController {
    private UserRepository userRepository;
    private LessonRepository lessonRepository;
    private UserService userService;
    private LessonService lessonService;
    @Autowired

    public SelectLessonController(UserRepository userRepository, LessonRepository lessonRepository, UserService userService, LessonService lessonService) {
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.userService = userService;
        this.lessonService = lessonService;
    }
    @GetMapping
    public String selectLesson(Model model) {
        model.addAttribute("lessons",lessonService.getAvailableLessons());

        return "/selectLesson";
    }
}
