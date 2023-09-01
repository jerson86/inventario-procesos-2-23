package com.procesos.inventario.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phone;
    private String password;
    private String document;
}
