package br.com.correios.controller;

import br.com.correios.exception.NoContentException;
import br.com.correios.exception.NotReadyException;
import br.com.correios.model.Address;
import br.com.correios.service.CorreiosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CorreiosController {

    @Autowired
    CorreiosService service;

    @GetMapping(value = "/status")
    public String getStatus(){
        return "Service status: " + service.getStatus();
    }

    @GetMapping(value = "/zipcode/{zipcode}")
    public Address getAddressByZipCode(@PathVariable String zipcode) throws NoContentException, NotReadyException {
        return service.getAddressByZipCode(zipcode);
    }
}
