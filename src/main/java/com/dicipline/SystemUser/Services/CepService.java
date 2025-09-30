package com.dicipline.SystemUser.Services;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.dicipline.SystemUser.Entities.Localization;

@Service
public class CepService {
    
    private final WebClient webclient;	
    
    public CepService(WebClient.Builder builder) {
        this.webclient = builder.
        		baseUrl("https://viacep.com.br/ws").
        		build();
    }
    
    public Localization buscarCep(String cep) {
        if (cep == null || cep.isBlank()) {
            throw new IllegalArgumentException("CEP inválido");
        }
        
        String cepLimpo = cep.replaceAll("\\D", "");
        
        if (cepLimpo.length() != 8) {
            throw new IllegalArgumentException("CEP deve ter 8 dígitos!");
        }
        
        Localization localization = webclient.get()
            .uri("/{cep}/json", cepLimpo)
            .retrieve()
            .bodyToMono(Localization.class)
            .block();
        
        if (localization == null) {
            throw new RuntimeException("Endereço não encontrado para o CEP informado");
        }
        
        return localization;
    }
}