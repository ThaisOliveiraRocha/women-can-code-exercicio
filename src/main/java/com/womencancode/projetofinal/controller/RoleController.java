package com.womencancode.projetofinal.controller;

import com.womencancode.projetofinal.model.Role;
import com.womencancode.projetofinal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/role", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

public class RoleController {

    @Autowired
    RoleService service;

    @PostMapping
    public ResponseEntity<Role> insertRole(@RequestBody Role role) throws Exception{
        return ResponseEntity.ok(service.insertRole(role));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@RequestBody Role role, @PathVariable String id) throws Exception {
        role.setId(id);
        return ResponseEntity.ok(service.updateRole(role));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getById(@PathVariable String id) throws Exception{
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Stream<Role>> getAll(@SortDefault.SortDefaults(
            {@SortDefault(sort = "name", direction = Sort.Direction.ASC)}) Pageable pageable ) {
        return ResponseEntity.ok(service.findAll(pageable).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
