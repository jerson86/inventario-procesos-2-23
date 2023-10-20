package com.procesos.inventario.service;

import com.procesos.inventario.exceptions.NotFoundException;
import com.procesos.inventario.model.Address;
import com.procesos.inventario.model.User;
import com.procesos.inventario.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserService userService;

    public Address createAddress(Address address, Long idUser){
        User user = userService.getUserById(idUser);
        address.setUser(user);
        return addressRepository.save(address);
    }

    public Address disabledAddress(Long id){
        if(id == 0){
            throw new NotFoundException("Address id is null");
        }
        Optional<Address> address = addressRepository.findById(id);
        if(address.isEmpty()){
            throw new NotFoundException("Address not found");
        }
        address.get().setStatus(Boolean.FALSE);
        return addressRepository.save(address.get());
    }

    public Address getAddressById(Long id){
        Optional<Address> address = addressRepository.findById(id);
        if(address.isEmpty()){
            throw new NotFoundException("Address not found");
        }
        return address.get();
    }

    public List<Address> getAllAddress(){
        List<Address> address = (List<Address>) addressRepository.findAll();
        if(address.isEmpty()){
            throw new NotFoundException("Address not found");
        }
        return address;
    }
}
