package com.example.proooject.Contollers.Clients;

import com.example.proooject.Model.Lesson;
import com.example.proooject.Model.User;
import com.example.proooject.Repository.LessonRepository;
import com.example.proooject.Repository.UserRepository;
import com.example.proooject.Service.LessonService;
import com.example.proooject.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@Controller
@RequestMapping("/pc/selectLesson")
public class SelectLessonController {
    LessonRepository lessonRepository;
    LessonService lessonService;
    UserService userService;
    UserRepository userRepository;
    @Autowired
    public SelectLessonController(LessonRepository lessonRepository, LessonService lessonService, UserService userService, UserRepository userRepository) {
        this.lessonRepository = lessonRepository;
        this.lessonService = lessonService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public String dt(Model model){
        User currentUser =  userService.getCurrentUser();
        log.info("Current user is " + currentUser.getName());
        List<Lesson> lessonList = lessonService.getAvailableLessons(currentUser);
        for (Lesson l: lessonList
             ) {log.info(l.toString());

        }
        model.addAttribute("lessons",lessonService.getAvailableLessons(currentUser));

        return "selectLesson";
    }
    @PostMapping()
    public String dtPost(@RequestParam("lessonId") Integer lessonId){
        User currentUser =  userService.getCurrentUser();
        log.info(lessonId.toString());
        lessonService.selectLesson(lessonRepository.findById(lessonId).get(),currentUser);
        return "redirect:/pc/selectLesson";
    }

}
