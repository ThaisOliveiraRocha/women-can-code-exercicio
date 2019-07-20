package com.womencancode.projetofinal.service;

import com.womencancode.projetofinal.exception.EntityNotFoundException;
import com.womencancode.projetofinal.exception.InvalideFieldException;
import com.womencancode.projetofinal.exception.ServiceException;
import com.womencancode.projetofinal.model.Role;
import com.womencancode.projetofinal.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role insertRole(Role role)throws ServiceException {
        validateInsert(role);
        return roleRepository.insert(role);
    }

    public Role updateRole(Role role) throws ServiceException{
        validateId(role.getId());
        return roleRepository.save(role);
    }

    public Page<Role> findAll(Pageable pageable){
        return roleRepository.findAll((org.springframework.data.domain.Pageable) pageable);
    }

    public  Role findById(String id) throws ServiceException{
        String message = String.format("User %s not found", id);
        return roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(message));
    }

    public void delete(String id) throws ServiceException{
        validateId(id);
        roleRepository.deleteById(id);
    }

    private void validateInsert(Role role) throws ServiceException{
        if(StringUtils.hasLength(role.getId()))
            throw new InvalideFieldException("id is an invalid parameter of the insert action");
        if(roleRepository.findByNameIgnoreCase(role.getName()).isPresent())
            throw new DuplicateKeyException(String.format("Name %s already exist", role.getName()));
    }

    private void validateId(String id) throws EntityNotFoundException{
        if(!roleRepository.findById(id).isPresent())
            throw new EntityNotFoundException(String.format("Role id %s not found", id));
    }
}
