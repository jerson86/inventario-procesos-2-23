package com.procesos.inventario.controller;

import com.procesos.inventario.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("users/{id}")
    public User getUserById(@PathVariable Long id){
        User user = new User(id,"Pepito","perez","calle 20","pepito@gmail.com","3150000000","123456","111111111");
        return user;
    }
}
