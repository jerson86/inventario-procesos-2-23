package com.procesos.inventario.service;

import com.procesos.inventario.exceptions.AlreadyExistsException;
import com.procesos.inventario.exceptions.NotFoundException;
import com.procesos.inventario.model.User;
import com.procesos.inventario.repository.UserRepository;
import com.procesos.inventario.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public User createUser(User user){
        Optional<User> existingUserByEmail = userRepository.findByEmail(user.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new AlreadyExistsException(Constants.USER_EMAIL_EXISTS.getMessage());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserById(Long id){
        if (id == 0){
            throw new NotFoundException("User id is null");
        }
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new NotFoundException("User not found");
        }
        return user.get();
    }

    public User updateUser(User user, Long id){
        Optional<User> userBd = userRepository.findById(id);
        if(userBd.isEmpty()){
            throw new NotFoundException("User not found");
        }
        if(!userBd.get().getEmail().equals(user.getEmail())){
            Optional<User> existingUserByEmail = userRepository.findByEmail(user.getEmail());
            if (existingUserByEmail.isPresent()) {
                throw new AlreadyExistsException(Constants.USER_EMAIL_EXISTS.getMessage());
            }
        }
        userBd.get().setFirstName(user.getFirstName());
        userBd.get().setLastName(user.getLastName());
        userBd.get().setPhone(user.getPhone());
        return userRepository.save(userBd.get());
    }

    public Boolean deleteUserById(Long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<User> findAllUsers(){
        return (List<User>) userRepository.findAll();
    }
}










