package com.events.serviceclient.repo.model;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long clientId;
    private String clientFirstName;
    private String clientSecondName;
    private String clientEmail;
    private String clientPhone;
    private String clientPassword;

    public Client() {
    }

    public Client(String clientFirstName, String clientSecondName, String clientEmail, String clientPhone, String clientPassword) {
        this.clientFirstName = clientFirstName;
        this.clientSecondName = clientSecondName;
        this.clientEmail = clientEmail;
        this.clientPhone = clientPhone;
        this.clientPassword = clientPassword;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientSecondName() {
        return clientSecondName;
    }

    public void setClientSecondName(String clientSecondName) {
        this.clientSecondName = clientSecondName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }
}
