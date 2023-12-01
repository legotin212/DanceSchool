package com.example.proooject.Service;

import com.example.proooject.Model.Lesson;
import com.example.proooject.Repository.LessonRepository;
import com.example.proooject.Repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class LessonServiceTest  {
    static final Integer ID = 1;
    @Mock
    EntityManager entityManager;
    @Mock
    LessonRepository lessonRepository;
    @Mock
    UserRepository userRepository;
    @InjectMocks
    LessonService lessonService;

    @Test
    void findLessonById_ShouldCallRepository(){
        final Lesson lesson = Mockito.mock(Lesson.class);
        Mockito.when(lessonRepository.findById(ID)).thenReturn(Optional.ofNullable(lesson));

        final Lesson actual = lessonService.findLessonById(ID);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(lesson,actual);
    }

    @Test
    void
//    @Test
//    void getAvailableLessons_ReturnsNotNull(){
//        Mockito.when(entityManager.createQuery(
//                "SELECT l FROM Lesson l WHERE l.isExpired!=:paramExp ",
//                Lesson.class
//        ).setParameter("paramExp",false).getResultList()).thenReturn(new ArrayList<Lesson>());
//
//        Assertions.assertNotEquals(new ArrayList<Lesson>(),lessonService.getAvailableLessons());
//    }
}
