package com.kameleon.task.quoteserver.service;

import com.kameleon.task.quoteserver.DAO.UserRepository;
import com.kameleon.task.quoteserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<User> getAllUsers(){
        return  userRepository.findAll();
    }

    public User getUserById(Integer id){
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    public void createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreationDate(new Date());
        userRepository.save(user);
    }

    public void updateOrCreate(User user){
        Optional<User> userOptional = userRepository.findById(user.getId());
        if(userOptional.isPresent()){
            User tempU = userOptional.get();
            tempU.setPassword(passwordEncoder.encode(user.getPassword()));
            tempU.setUsername(user.getUsername());
            tempU.setEmail(user.getEmail());
            tempU.setCreationDate(new Date());
            userRepository.save(tempU);
        }
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }
}
