package com.dicipline.SystemUser.Services;

import org.springframework.stereotype.Service;

import com.dicipline.SystemUser.Entities.DataUser;
import com.dicipline.SystemUser.Entities.Localization;

@Service
public class DataUserFactory {
    
    private final CepService cepService;
    
    public DataUserFactory(CepService cepService) {
        this.cepService = cepService;
    }    
    public DataUser createLocalization(DataUser dataUser) {
        Localization localization = cepService.buscarCep(dataUser.getCep());
        dataUser.setLocalization(localization);
        return dataUser;
    }
}