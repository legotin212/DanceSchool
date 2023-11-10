package com.example.proooject.Contollers;

import com.example.proooject.ClientRegForm;
import com.example.proooject.Model.Client;
import com.example.proooject.Repository.ClientRepository;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Arrays;
import java.util.List;
@Slf4j
@Controller
@SessionAttributes("client")
public class HomeController {

    private ClientRepository clientRepository;
    @Autowired
    public HomeController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @ModelAttribute
    public Client client(){
        return new Client();
    }
    @GetMapping("/")
    public String homePage(Model model){
        Client myCl = clientRepository.findById("10").get();
        log.info(myCl.toString());
        return "home";
    }
    @PostMapping("/")
    public String CreateClient(Model model, @Valid ClientRegForm clientRegForm
    , Errors errors){
        if (errors.hasErrors()) {
            return "home";
        }
        Client newCl = clientRegForm.toClient();
        log.info("saving user");
        clientRepository.save(newCl);
        return "home";
    }
//    @PostMapping("/redirect")

}
