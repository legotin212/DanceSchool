package com.example.proooject.Contollers;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.List;
@Slf4j
@Controller
public class DynamicTableController {
    LessonRepository lessonRepository;
    LessonService lessonService;
    UserService userService;
    UserRepository userRepository;
    @Autowired
    public DynamicTableController(LessonRepository lessonRepository, LessonService lessonService, UserService userService, UserRepository userRepository) {
        this.lessonRepository = lessonRepository;
        this.lessonService = lessonService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/dt")
    public String dt(Model model){
        List<Lesson> lessonList = lessonService.getAvailableLessons();
        for (Lesson l: lessonList
             ) {log.info(l.toString());

        }
        model.addAttribute("lessons",lessonService.getAvailableLessons());

        return "dynamicTable";
    }
    @PostMapping("/dt")
    public String dtPost(@ModelAttribute("selectedLesson") Integer selectedLessonId){
        User currentUser =  userService.getCurrentUser();
        lessonService.addClientToLesson(lessonRepository.findById(selectedLessonId).get(),currentUser);
//        for (Integer id:lessonId
//             ) {lessonService.addClientToLesson(lessonRepository.findById(id).get(),currentUser);
//
//        }

        return "dynamicTable";
    }

}
