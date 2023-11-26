package com.example.proooject.Service;

import com.example.proooject.Model.Role;
import com.example.proooject.Model.User;
import com.example.proooject.Repository.RoleRepository;
import com.example.proooject.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
@Slf4j
@ExtendWith(MockitoExtension.class)
//@DataJpaTest
class UserServiceTest  {
    @InjectMocks
    private UserService userService;

//    @Mock
//    private Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
//    @Mock
//    private LessonService lessonService;
//    @Mock
//    private EntityManager entityManager;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void Test_saveNewUser_ifNoSuchUsersExist(){
        User testUser = new User("test","test","test","test");
        testUser.setId(1);
        Role testRole = new Role(1,"ROLE_USER");

        Mockito.when(userRepository.findByUsername(testUser.getUsername())).thenReturn(null);
        Mockito.when(roleRepository.findById(1)).thenReturn(Optional.of(testRole));



        Assertions.assertEquals(true,userService.saveNewUser(testUser));

    }
    @Test
    void Test_saveNewUser_IfSuchUserAlreadyExists(){
        User testUser = new User("test","test","test","test");
        testUser.setId(1);
        Role testRole = new Role(1,"ROLE_USER");

        Mockito.when(userRepository.findByUsername(testUser.getUsername())).thenReturn(testUser);
//        Mockito.when(roleRepository.findById(1)).thenReturn(Optional.of(testRole));



        Assertions.assertEquals(false,userService.saveNewUser(testUser));

    }
    @Test
    void Test_saveNewUserAsCoach_ifNoSuchUsersExist(){
        User testUser = new User("test","test","test","test");
        testUser.setId(1);
        Role testRole = new Role(3,"ROLE_COACH");

        Mockito.when(userRepository.findByUsername(testUser.getUsername())).thenReturn(testUser);
//        Mockito.when(roleRepository.findById(1)).thenReturn(Optional.of(testRole));



        Assertions.assertEquals(false,userService.saveNewUser(testUser));

    }
    @Test
    void Test_saveNewUserCoach_IfSuchUserAlreadyExists(){
        User testUser = new User("test","test","test","test");
        testUser.setId(1);
        Role testRole = new Role(3,"ROLE_COACH");

        Mockito.when(userRepository.findByUsername(testUser.getUsername())).thenReturn(testUser);
//        Mockito.when(roleRepository.findById(1)).thenReturn(Optional.of(testRole));



        Assertions.assertEquals(false,userService.saveNewUser(testUser));

    }
}
