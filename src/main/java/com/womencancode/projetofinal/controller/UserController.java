package com.womencancode.projetofinal.controller;

import com.womencancode.projetofinal.model.User;
import com.womencancode.projetofinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;


@RestController
@RequestMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    UserService service;

    @PostMapping
    public ResponseEntity<User> insertUser(@RequestBody User user) throws Exception{
        return ResponseEntity.ok(service.insertUser(user)); //pode dar errado
    }

    @PutMapping("/{id}")
    public ResponseEntity <User> updateUser(@RequestBody User user, @PathVariable String id) throws Exception{
        user.setId(id);
        return ResponseEntity.ok(service.updateUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity <User> getById(@PathVariable String id) throws Exception{
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Stream<User>> getAll(@SortDefault.SortDefaults(
            {@SortDefault(sort = "name", direction = Sort.Direction.ASC)}) Pageable pageable ) {
        return ResponseEntity.ok(service.findAll(pageable).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <String> delete (@PathVariable String id) throws Exception{
        service.delete(id);
        return ResponseEntity.ok(String.format("Excluido com Sucesso!!!"));//return ResponseEntity.noContent().build();
    }

}
