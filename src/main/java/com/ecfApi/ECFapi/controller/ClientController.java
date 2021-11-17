package com.ecfApi.ECFapi.controller;

import com.ecfApi.ECFapi.model.Client;
import com.ecfApi.ECFapi.repository.ClientRepository;
import com.ecfApi.ECFapi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;
    //CREATE
    //http://localhost:8080/createClient
    @PostMapping("/createClient")
    public void createClient(@RequestBody Client client){
        clientService.createClient(client);

    }

    //find client by id
    //http://localhost:8080/findClient=<id>
    @GetMapping("/findClient={id}")
    public Optional<Client> findClient(@PathVariable Long id) {
        return clientService.findClientById(id);
    }

    //update client
    //http://localhost:8080/updateClient
    @PutMapping ("/updateClient")
    public void updateClient(@RequestBody Client client){
        clientService.updateClient(client);

    }

    //delete client
    //http://localhost:8080/deleteClient=<id>
    @DeleteMapping("/deleteClient={id}")
    public void deleteClient(@PathVariable Long id ){
        clientService.deleteClient(clientService.findClientById(id).get());

    }


    //age of client
    //http://localhost:8080/AgeClient=<id>
    @GetMapping("/AgeClient={id}")
    public int getClientAge(@PathVariable Long id){
        return clientService.findClientAge(id);

    }
    //list of clients
    // http://localhost:8080/allClients
    @GetMapping("/allClients")
    public List<Client> getClients() {
        List<Client> clients = clientService.findAllClients();
        return clients;
    }

    // list of client with FirstName
// http://localhost:8080/clientWithFirstName=<name>
    @GetMapping("/clientWithFirstName={name}")
    public List<Client> getListOfClientWithFirstName(@PathVariable String name){
        return clientService.findAllClientWithFirstName(name);
    }



}
