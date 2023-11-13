package com.example.proooject.Contollers;

import com.example.proooject.Repository.UserRepository;
import com.example.proooject.Repository.CoachRepository;
import com.example.proooject.Repository.LessonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/selectLesson")
public class SelectLessonController {
    private UserRepository userRepository;
    private LessonRepository lessonRepository;
    private CoachRepository coachRepository;
    @Autowired
    public SelectLessonController(UserRepository userRepository, LessonRepository lessonRepository
    , CoachRepository coachRepository) {
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.coachRepository = coachRepository;
    }

    @GetMapping
    public String selectLesson(){

        return "/selectLesson";
    }

}
