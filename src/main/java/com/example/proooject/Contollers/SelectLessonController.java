package com.example.proooject.Contollers;

import com.example.proooject.Model.Client;
import com.example.proooject.Model.Coach;
import com.example.proooject.Model.Lesson;
import com.example.proooject.Repository.ClientRepository;
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
    private ClientRepository clientRepository;
    private LessonRepository lessonRepository;
    private CoachRepository coachRepository;
    @Autowired
    public SelectLessonController(ClientRepository clientRepository, LessonRepository lessonRepository
    , CoachRepository coachRepository) {
        this.clientRepository = clientRepository;
        this.lessonRepository = lessonRepository;
        this.coachRepository = coachRepository;
    }

    @GetMapping
    public String selectLesson(){
       Coach coach = coachRepository.findById("1").get();
       Lesson lesson = lessonRepository.findById("1").get();
       lesson.addCoachToLesson(coach);
       lessonRepository.save(lesson);
       coachRepository.save(coach);

        return "/selectLesson";
    }

}
