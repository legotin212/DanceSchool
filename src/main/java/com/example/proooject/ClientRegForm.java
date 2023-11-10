package com.example.proooject;

import com.example.proooject.Model.Client;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientRegForm {
    @NotNull
    @Size(min = 5,message = "YEBOK")
    private String name;
    private String lastname;

    public Client toClient(){
        return new Client(name,lastname);
    }
}
