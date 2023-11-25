package com.example.proooject.Service;

import com.example.proooject.Model.Lesson;
import com.example.proooject.Model.User;
import com.example.proooject.Repository.LessonRepository;
import com.example.proooject.Repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void selectLesson(Lesson lesson, User user)throws Exception{

        addClientToLesson(lesson,user);

    }
    public Lesson findLessonById(Integer lessonId){
        Optional<Lesson> lessonFromDb = lessonRepository.findById(lessonId);
        return lessonFromDb.orElse(new Lesson());
//        ?????
    }
    @Transactional
    public void addClientToLesson(Lesson lesson, User user){
        lesson.addClient(user);
        user.getSubscription().visit();
        user.addLesson(lesson);
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
    public List<Lesson> getAvailableLessons(){
        entityManager.createQuery(
                "SELECT l FROM LESSONS WHERE L.ISEXPIRED!=:paramEp ",
                Lesson.class
        ).setParameter("paramEp",false).getResultList();

//        entityManager.createQuery(
//                        "SELECT u FROM User u LEFT JOIN u.roles r WHERE r.id = :paramId",
//                        User.class
//                )
//                .setParameter("paramId", 3)
//                .getResultList();

    }



}
