package com.ecfApi.ECFapi.service;

import com.ecfApi.ECFapi.model.Client;
import com.ecfApi.ECFapi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;



    // todo service for create of patient
    public void createClient(Client client) { clientRepository.save(client); }



    // todo service for find patient by id
    public Optional<Client> findClientById(Long id) { return clientRepository.findById(id); }




    // todo service for update of patient
    public void updateClient(Client client) {
        Client client1=clientRepository.findById(client.getId())
                .orElseThrow(() -> new RuntimeException("client not found") );
        client1.setFirstName(client.getFirstName());
        client1.setLastName(client.getLastName());
        client1.setBirthdate(client.getBirthdate());
        clientRepository.save(client1); }



    // todo service for delete of patient
    public void deleteClient(Client client) { clientRepository.delete(client); }





    // todo service of calcule of age
    public Integer findClientAge(Long id) {
        Integer year=clientRepository.findById(id).get().getBirthdate().getYear();
        Integer nowYear = LocalDate.now().getYear();
        Integer age= nowYear - year;
        return age; }




    // todo Service for list of patients
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }



    // todo Service for findAllClientsWithFirstName
    public List<Client> findAllClientWithFirstName(String name) {
        return clientRepository.findAll().stream()
                .filter(patient -> patient.getLastName().equalsIgnoreCase(name))
                .collect(Collectors.toList()); }


}
