package com.ecfApi.ECFapi.service;

import com.ecfApi.ECFapi.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class ClientServiceTest {
    @Autowired
    private ClientService clientService;
    Client client=new Client("firstName","lastName", LocalDate.of(1996,01,12));


    @Test
    void createClientTest(){
        clientService.createClient(client);
        Assertions.assertEquals("lastName", clientService.findClientById(client.getId()).get().getLastName());
        clientService.deleteClient(client);
    }


    @Test
    void updateClientTest() {
        clientService.createClient(client);
        client.setLastName("lastName updated");
        clientService.updateClient(client);
        Assertions.assertEquals("lastName updated", clientService.findClientById(client.getId()).get().getLastName());
        clientService.deleteClient(client);
    }



    @Test
    void deleteClientTest(){
        clientService.createClient(client);
        long id=client.getId();
        clientService.deleteClient(client);
        Assertions.assertFalse(clientService.findClientById(id).isPresent());
    }


    @Test
    void findAllClientsTest() {
        clientService.createClient(client);
        Assertions.assertTrue(clientService.findAllClients().size() > 0);
        clientService.deleteClient(client);
    }

    @Test
    void findClientAgeTest() {
        clientService.createClient(client);
        Assertions.assertEquals(1, clientService.findClientAge(client.getId()));
        clientService.deleteClient(client);
    }

    @Test
    void findAllClientsWithSameFirstNameTest() {
        clientService.createClient(client);
        clientService.createClient(new Client("lastName", "second", LocalDate.of(1996, 01, 12)));
        Client secondPatient = clientService.findAllClients().stream().filter(patient1 -> patient1.getFirstName().equals("second")).findFirst().get();
        clientService.findAllClientWithFirstName("lastName");
        Assertions.assertTrue(clientService.findAllClientWithFirstName("lastName").size() > 0);
        clientService.deleteClient(client);
        clientService.deleteClient(secondPatient);

    }

}
