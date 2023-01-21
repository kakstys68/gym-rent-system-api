package com.rentsystemspring.controllers;

import com.google.gson.Gson;
import com.rentsystemspring.errors.ClientNotFound;
import com.rentsystemspring.model.Client;
import com.rentsystemspring.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Properties;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping(value = "/allClients")
    public @ResponseBody Iterable<Client> getAllClients(){
        return clientRepository.findAll();
    }

    @GetMapping(value = "/clientById/{id}")
    public EntityModel<Client> getClientById(@PathVariable(name = "id") int id){
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFound(id));
        return EntityModel.of(client, linkTo(methodOn(ClientController.class).getClientById(id)).withSelfRel());
    }
    @PostMapping("/addClient")
    Client newClient(@RequestBody Client newClient) {
        return clientRepository.save(newClient);
    }

    @PutMapping(value = "/updateClient/{id}")
    public @ResponseBody String updateClient(@RequestBody String clientInfoToUpdate, @PathVariable int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(clientInfoToUpdate, Properties.class);
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFound(id));
        client.setName(properties.getProperty("name"));
        client.setSurname(properties.getProperty("surname"));
        client.setUsername(properties.getProperty("username"));
        client.setPassword(properties.getProperty("password"));
        client.setEmail(properties.getProperty("email"));
        client.setBirthDate(LocalDate.parse(properties.getProperty("birthDate")));
        client.setAdmin(Boolean.parseBoolean(properties.getProperty("admin")));
        clientRepository.save(client);
        return "Client updated";
    }

    @PostMapping(value = "/validateClient")
    public @ResponseBody EntityModel<Client> validateClient(@RequestBody String info){
        Gson parser = new Gson();
        Properties properties = parser.fromJson(info, Properties.class);
        Client client = clientRepository.findClientByUsernameAndPassword(properties.getProperty("username"), properties.getProperty("password"));
        return EntityModel.of(client, linkTo(methodOn(ClientController.class).getAllClients()).withRel("AllClients"));
    }

    @DeleteMapping(value = "/deleteClient/{id}")
    public @ResponseBody String deleteClient(@PathVariable(name = "id") int id){
        clientRepository.deleteById(id);
        return "Client successfully deleted";
    }

}
