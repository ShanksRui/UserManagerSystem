package com.dicipline.SystemUser.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dicipline.SystemUser.Entities.User;
import com.dicipline.SystemUser.Repositories.UserRepository;
import com.dicipline.SystemUser.Services.Exceptions.DataErrorException;
import com.dicipline.SystemUser.Services.Exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
    private UserRepository repository;
    
    public List<User> findAll() {
        return this.repository.findAll();
    }
    
    public User findById(Long id) {
        Optional<User> obj = this.repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    
    public User insert(User user) {
        return this.repository.save(user);
    }
    
    public void delete(Long id) {
        if (!this.repository.existsById(id))
            throw new ResourceNotFoundException(id);
        try {
            this.repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataErrorException(e.getMessage());
        }
    }
    
    public User update(Long id, User user) {
        try {
            User entity = this.repository.getReferenceById(id);
            dataUpdate(entity, user);
            return this.repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
    
    public void dataUpdate(User entity, User user) {
        entity.setName(user.getName());
        entity.setPassword(user.getPassword());
        entity.setBirthDate(user.getBirthDate());
    }
}

