package com.example.demo.Services;

import com.example.demo.DTO.SecretariatDTO;
import com.example.demo.Entites.Secretariat;
import com.example.demo.Repositories.DateRepository;
import com.example.demo.Repositories.SecretariantRepository;
import com.example.demo.Util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SercretariatService {

    @Autowired
    private SecretariantRepository secretariationRepository;

    @Autowired
    private Converter converter;

    @Autowired
    private DateRepository dateRepository;

    public SecretariatDTO createSecretariat(SecretariatDTO secretariatDTO){
        Secretariat secretariat = new Secretariat();
        secretariat.setName(secretariatDTO.name());
        secretariat.setLastName(secretariatDTO.lastName());
        secretariat.setEmail(secretariatDTO.email());

        return converter.converToSecretariantDTO(secretariationRepository.save(secretariat));
    }



}
