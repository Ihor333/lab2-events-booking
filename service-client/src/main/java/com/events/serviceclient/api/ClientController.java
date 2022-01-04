package com.events.serviceclient.api;

import com.events.serviceclient.api.dto.ClientDto;
import com.events.serviceclient.repo.model.Client;
import com.events.serviceclient.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> index(){
        final List<Client> users = clientService.getAllClients();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> show(@PathVariable long id){
        try {
            final Client client = clientService.getClientById(id);
            return ResponseEntity.ok(client);
        } catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ClientDto newClient){
        final String firstName = newClient.getClientFirstName();
        final String secondName = newClient.getClientSecondName();
        final String email = newClient.getClientEmail();
        final String phone = newClient.getClientPhone();
        final String password = newClient.getClientPassword();

        final long clientId = clientService.addClient(firstName, secondName, email, phone, password);
        final String clientUri = String.format("/client/%d", clientId);
        return ResponseEntity.created(URI.create(clientUri)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> change(@PathVariable long id, @RequestBody ClientDto newClient){
        final String firstName = newClient.getClientFirstName();
        final String secondName = newClient.getClientSecondName();
        final String email = newClient.getClientEmail();
        final String phone = newClient.getClientPhone();
        final String password = newClient.getClientPassword();

        try{
            clientService.updateClientById(id, firstName, secondName, email, phone, password);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

}
