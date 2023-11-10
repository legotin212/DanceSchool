package com.example.proooject.Repository;

import com.example.proooject.Model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface ClientRepository extends CrudRepository<Client,String> {
}
