package com.dicipline.SystemUser.Services;

import java.util.List;

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
    public List<DataUser> createLocalizationAll(List<DataUser> listD) {
    	Localization localization;
    	for(DataUser d : listD) {
          localization = cepService.buscarCep(d.getCep());
	      d.setLocalization(localization);
    	}
		return listD;
    }
}