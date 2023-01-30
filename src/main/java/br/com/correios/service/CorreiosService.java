package br.com.correios.service;

import br.com.correios.CorreiosApplication;
import br.com.correios.exception.NoContentException;
import br.com.correios.exception.NotReadyException;
import br.com.correios.model.Address;
import br.com.correios.model.AddressStatus;
import br.com.correios.model.Status;
import br.com.correios.repository.AddressRepository;
import br.com.correios.repository.AddressStatusRepository;
import br.com.correios.repository.SetupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class CorreiosService {

    private static Logger logger = LoggerFactory.getLogger(CorreiosService.class);

    @Autowired
    private AddressRepository repository;

    @Autowired
    private AddressStatusRepository statusRepository;

    @Autowired
    private SetupRepository setupRepository;

    public Status getStatus(){
        return statusRepository.findById(AddressStatus.DEFAULT_ID).
                orElse(AddressStatus.builder().status(Status.NEED_SETUP).build())
                .getStatus();
    }

    public Address getAddressByZipCode(String zipcode) throws NoContentException, NotReadyException{
        if(!this.getStatus().equals(Status.READY)){
            throw new NotReadyException();
        }
        return repository.findById(zipcode).orElseThrow(NoContentException::new);

    }

    private void saveStatus(Status status){
        statusRepository.save(AddressStatus.builder()
                .id(AddressStatus.DEFAULT_ID)
                .status(status)
                .build());
    }

    @EventListener(ApplicationStartedEvent.class)
    protected void setupOnStartup(){

        try {
            this.setUp();
        }catch (Exception e){
            CorreiosApplication.close(999);
            logger.error("Something went wrong, closing the application - ", e);
        }
    }

    public void setUp() throws Exception{
        logger.info("------");
        logger.info("------");
        logger.info("------ SETUP RUNNING ");
        logger.info("------");
        logger.info("------");

        if(this.getStatus().equals(Status.NEED_SETUP)){
            saveStatus(Status.RUNNING);

            try {
                repository.saveAll(setupRepository.getFromOrigin());
            }catch (Exception e){
                this.saveStatus(Status.NEED_SETUP);
                throw e;
            }

            saveStatus(Status.READY);
        }
        logger.info("------");
        logger.info("------");
        logger.info("------ SETUP READY ");
        logger.info("------");
        logger.info("------");
    }


}
