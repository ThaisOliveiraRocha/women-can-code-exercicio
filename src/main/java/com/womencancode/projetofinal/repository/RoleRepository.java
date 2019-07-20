package com.womencancode.projetofinal.repository;

import com.womencancode.projetofinal.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository <Role, String> {
    Optional<Role> findByNameIgnoreCase(String name);
}
