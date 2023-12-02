package com.example.proooject.Contollers.Clients;

import com.example.proooject.Model.Lesson;
import com.example.proooject.Model.Subscription;
import com.example.proooject.Model.User;
import com.example.proooject.Service.LessonService;
import com.example.proooject.Service.SubscriptionService;
import com.example.proooject.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Slf4j
@Controller
public class ClientController {

   private UserService userService;
   private LessonService lessonService;
   private SubscriptionService subscriptionService;
    @Autowired
    public ClientController(UserService userService, LessonService lessonService, SubscriptionService subscriptionService) {
        this.userService = userService;
        this.lessonService = lessonService;
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/pc")
    public String clientController(Model model){
     User user =  userService.getCurrentUser();
     model.addAttribute("user",user);
     model.addAttribute("lessons",user.getLessons());

        return "pc";
        }

      @PostMapping("/pc")
        public String selectLesson(@RequestParam Integer lesson_id) throws Exception {

        lessonService.selectLesson(lessonService.findLessonById(lesson_id),userService.getCurrentUser());

        return "pc";
      }
    }

