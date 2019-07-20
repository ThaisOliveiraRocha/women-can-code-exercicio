package com.womencancode.projetofinal.service;

//import org.springframework.dao.DuplicateKeyException;

import com.womencancode.projetofinal.exception.DuplicateKeyException;
import com.womencancode.projetofinal.exception.EntityNotFoundException;
import com.womencancode.projetofinal.exception.InvalideFieldException;
import com.womencancode.projetofinal.exception.ServiceException;
import com.womencancode.projetofinal.model.User;
import com.womencancode.projetofinal.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User insertUser (User user) throws ServiceException {
        validateInsert(user);
        return userRepository.insert(user);
    }

    public User updateUser (User user) throws ServiceException{
        validateId(user.getId());
        return userRepository.save(user);

    }

    public Page<User> findAll(Pageable pageable){
        return userRepository.findAll((org.springframework.data.domain.Pageable) pageable);
    }

    public User findById(String id) throws ServiceException{
        String message = String.format("user %s not found", id);
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(message));
    }

    public void delete(String id) throws ServiceException{
        validateId(id);
        userRepository.deleteById(id);
    }

    private void validateId(String id) throws EntityNotFoundException {
        if(!userRepository.findById(id).isPresent())
            throw new EntityNotFoundException(String.format("User %s not found", id));
    }

    private void validateInsert(User user) throws ServiceException{
        if(StringUtils.hasLength(user.getId()))
            throw new InvalideFieldException("id is an invalid parameter of the insert action");
        if(userRepository.findByUsername(user.getUsername()).isPresent())
            throw new DuplicateKeyException(String.format("Username %s already exist", user.getUsername()));
    }
}
