package com.procesos.inventario.service;

import com.procesos.inventario.model.User;
import com.procesos.inventario.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).get();
    }

    public User updateUser(User user, Long id){
        if(userRepository.existsById(id)){
            User userBd = userRepository.findById(id).get();
            userBd.setFirstName(user.getFirstName());
            userBd.setLastName(user.getLastName());
            userBd.setAddress(user.getAddress());
            userBd.setEmail(user.getEmail());
            userBd.setPhone(user.getPhone());
            return userRepository.save(userBd);
        }
        return null;
    }

    public Boolean deleteUserById(Long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
