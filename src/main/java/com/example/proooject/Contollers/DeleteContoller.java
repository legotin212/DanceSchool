package com.example.proooject.Contollers;

import com.example.proooject.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Slf4j
@Controller
public class DeleteContoller {
    UserService userService;
    @Autowired

    public DeleteContoller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("admin/deleteUser")
    public String deleteUser(){
        return "deleteUser";
    }

    @PostMapping("admin/deleteUser")
    public String delete(@RequestParam String usernamefordelete){
        log.info("delete method");
        userService.deleteUserByUsername(usernamefordelete);

        return "redirect:/Admin";

    }
}
