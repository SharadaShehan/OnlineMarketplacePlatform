package com.nebulamart.userservice.template;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourierSignUpDTO {

    private String name;
    private String email;
    private String password;
    private String contactNumber;
    private String logoUrl;

    public boolean isValid() {
        return name != null && email != null && password != null && contactNumber != null;
    }
}
