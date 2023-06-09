package com.interpackage.users.service;

import com.interpackage.users.model.Response;
import com.interpackage.users.model.Role;
import com.interpackage.users.model.User;
import com.interpackage.users.repository.RoleRepository;
import com.interpackage.users.repository.UserRepository;
import com.interpackage.users.util.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User createUser(User user) throws Exception{

        if(getUserByName(user.getName())!=null){
            throw new Exception("User name already exits");
        }

        if(getUserByEmail(user.getEmail())!=null){
            throw new Exception("email already registered");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Page<User> getAllUser(int page, int size,boolean pagination) {
       return  userRepository.findAll(pagination ? PageRequest.of(page,size) : Pageable.unpaged());
    }

    public List<User> getAllClients(){
        return userRepository.findAllByRoleIdRole(Constants.ID_ROLE_CLIENT);
    }

    public User updateUser(User user, String name) throws Exception{
        var usrDb = getUserByName(name);
        if(usrDb==null){
            throw new Exception("User not found");
        }
        usrDb.merge(user);
        return  userRepository.save(usrDb);
    }

    public void deleteUser(String name) throws Exception{
        var usrDb= getUserByName(name);
        if(usrDb==null){
            throw new Exception("User not found");
        }
        userRepository.delete(usrDb);
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name).orElse(null);
    }

    public User getUserByEmail(String name) {
        return userRepository.findByEmail(name).orElse(null);
    }

    public ResponseEntity<Response> getClientByDpi(String dpi){
        try {
                Role role = new Role();
                role.setIdRole(Constants.ID_ROLE_CLIENT);
                Optional<User> userOptional =
                        this.userRepository
                                .findByDpiAndRole(dpi, role);
                return userOptional
                        .map(user ->
                                ResponseEntity.ok(new Response(user)))
                        .orElseGet(() ->
                                ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }
}
