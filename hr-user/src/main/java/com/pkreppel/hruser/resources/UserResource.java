package com.pkreppel.hruser.resources;

import com.pkreppel.hruser.entities.User;
import com.pkreppel.hruser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User obj = repository.findById(id).get();
        return ResponseEntity.ok(obj);
    }

    @GetMapping(value = "/generatepassword/{password}")
    public ResponseEntity<String> generatePassword(@PathVariable String password){
        return ResponseEntity.ok(bCryptPasswordEncoder.encode(password));
    }

    @GetMapping(value = "/search")
    public ResponseEntity<User> findByEmail(@RequestParam String email){
        User obj = repository.findByEmail(email);
        return ResponseEntity.ok(obj);
    }
}
