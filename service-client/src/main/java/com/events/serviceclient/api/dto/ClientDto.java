package com.events.serviceclient.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientDto {
    private String clientFirstName;
    private String clientSecondName;
    private String clientEmail;
    private String clientPhone;
    private String clientPassword;
}
