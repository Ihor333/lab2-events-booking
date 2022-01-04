package com.events.serviceclient.service;

import com.events.serviceclient.api.dto.OrderDto;
import com.events.serviceclient.repo.ClientRepository;
import com.events.serviceclient.repo.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class ClientService {
    private final ClientRepository clientRepository;

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public Client getClientById(long id) throws IllegalArgumentException {
        Optional<Client> client = clientRepository.findById(id);
        if(client.isEmpty()) throw new IllegalArgumentException("Client doesn`t exist");
        else return client.get();
    }

    public long addClient(String name, String surname, String email , String phone, String password){
        final Client newClient = new Client(name,surname,email,phone,password);
        final Client addedClient = clientRepository.save(newClient);
        return addedClient.getClientId();
    }

    public void updateClientById(long id, String name, String surname, String email , String phone, String password){
        final Optional<Client> client = clientRepository.findById(id);
        if(client.isEmpty()) throw new IllegalArgumentException("Client doesn`t exist");
        final Client clientForUpdate = client.get();

        if (name != null && !name.isBlank()) clientForUpdate.setClientFirstName(name);
        if (surname != null && !surname.isBlank()) clientForUpdate.setClientSecondName(surname);
        if (email != null && !email.isBlank()) clientForUpdate.setClientEmail(email);
        if (phone != null && !phone.isBlank()) clientForUpdate.setClientPhone(phone);
        if (password != null && !password.isBlank()) clientForUpdate.setClientPassword(password);
        clientRepository.save(clientForUpdate);
    }

    public void deleteClient(long id){
        RestTemplate restTemplate = new RestTemplate();
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent()) {
            String getOrderLink = "http://service-order:8080/order/client/" + client.get().getClientId();
            OrderDto[] orderList = restTemplate.getForObject(getOrderLink, OrderDto[].class);
            if(orderList != null) {
                for (OrderDto order : orderList) {
                    restTemplate.delete("http://service-order:8080/order/" + order.getOrderId());
                }
            }
        }
        clientRepository.deleteById(id);
    }

}
