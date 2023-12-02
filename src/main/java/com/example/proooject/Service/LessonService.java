package com.example.proooject.Service;

import com.example.proooject.Model.Lesson;
import com.example.proooject.Model.User;
import com.example.proooject.Repository.LessonRepository;
import com.example.proooject.Repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LessonService {
    LessonRepository lessonRepository;
    UserRepository userRepository;
    EntityManager entityManager;

    @Autowired
    public LessonService(LessonRepository lessonRepository, UserRepository userRepository, EntityManager entityManager) {
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
        this.entityManager = entityManager;

    }

    public void selectLesson(Lesson lesson, User user){

        addClientToLesson(lesson,user);

    }

    public void saveNewLesson(String name, Calendar date,User coach){
        Lesson lesson = new Lesson(name,date,false);
        addCoachToLesson(lesson,coach);

    }
    public Lesson findLessonById(Integer lessonId){
        Optional<Lesson> lessonFromDb = lessonRepository.findById(lessonId);
        return lessonFromDb.orElse(new Lesson());
//        ?????
    }
    @Transactional
    public void addClientToLesson(Lesson lesson, User user){
        lesson.addClient(user);
        log.info("client added");
        user.getSubscription().visit();
        log.info("sub visit");
        user.addLesson(lesson);
        log.info("lesson added");
//        lessonRepository.save(lesson);
        lessonRepository.save(lesson);
        userRepository.save(user);


    }
    @Transactional
    public void removeClientFromLesson(Lesson lesson,User user){
        lesson.removeClient(user);
        user.removeLesson(lesson);
        lessonRepository.save(lesson);
        userRepository.save(user);
    }
    @Transactional
    public void addCoachToLesson(Lesson lesson,User coach){
        lesson.addCoach(coach);
        coach.addLessonAsCoach(lesson);
        lessonRepository.save(lesson);
        userRepository.save(coach);
    }
    @Transactional
    public void removeCoachFromLesson(Lesson lesson,User coach){
        lesson.removeCoach(coach);
        coach.removeLessonAsCoach(lesson);
        lessonRepository.save(lesson);
        userRepository.save(coach);
    }
    public List<Lesson> getAvailableLessons(User user){
        log.info("getavless method");

        List<Lesson> t = entityManager.createQuery(
                "SELECT l FROM Lesson l WHERE l.isExpired!=:paramExp ",
                Lesson.class
        ).setParameter("paramExp",true).getResultList();//Возвращает список непросроченных занятий

        List<Lesson> resultList  = t.stream().filter(lesson -> !lesson.hasUser(user)).toList();//Возвращает те занятия, на которые пользователь ещё не записан

        return  resultList;

    }



}
