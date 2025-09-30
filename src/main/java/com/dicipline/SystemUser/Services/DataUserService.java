package com.dicipline.SystemUser.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dicipline.SystemUser.Entities.DataUser;
import com.dicipline.SystemUser.Repositories.DataUserRepository;
import com.dicipline.SystemUser.Services.Exceptions.DataErrorException;
import com.dicipline.SystemUser.Services.Exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DataUserService {
   
   private final DataUserRepository repository;
   
   private final DataUserFactory factory;
   
   public DataUserService(DataUserFactory factory,DataUserRepository repository ) {
	   this.factory = factory;
	   this.repository = repository;
   }
    
    public List<DataUser> findAll() {
        return repository.findAll();
    }
    
    public DataUser findById(Long id) {
    	 Optional<DataUser> obj = repository.findById(id);
         return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    
    public DataUser insert(DataUser dataUser) {
    	dataUser = factory.createLocalization(dataUser);
        return repository.save(dataUser);
    }
    
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataErrorException(e.getMessage());
        }
    }
    
    public DataUser update(Long id, DataUser du) {
        try {
            DataUser entity = repository.getReferenceById(id);
            dataUpdate(entity, du);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
    
    public void dataUpdate(DataUser entity, DataUser du) {
        entity.setCep(du.getCep());
        entity.setLogin(du.getLogin());
        entity.setLoginClosed(du.getLoginClosed());
        entity.setSystemOperational(du.getSystemOperational());
    }
}

