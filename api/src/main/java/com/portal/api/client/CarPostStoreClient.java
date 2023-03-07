package com.portal.api.client;

import com.portal.api.dto.CarPostDTO;
import com.portal.api.dto.OwnerPostDTO;
import com.portal.api.service.CarPostStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class CarPostStoreClient {

    private final Logger LOG = (Logger) LoggerFactory.getLogger(CarPostStoreClient.class);

    private final String USER_STORE_SERVICE_URI = "http://localhost:8080/user";
    private final String POSTS_STORE_SERVICE_URI = "http://localhost:8080/sales";


    @Autowired
    RestTemplate restTemplate;

    public List<CarPostDTO> carForSalesClient(){
        ResponseEntity<CarPostDTO[]> responseEntity = restTemplate.getForEntity(POSTS_STORE_SERVICE_URI +"/cars", CarPostDTO[].class);
        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    public  void ownerPostClient(OwnerPostDTO newUser){
        restTemplate.postForEntity(USER_STORE_SERVICE_URI, newUser, OwnerPostDTO.class);
    }

    public void changeCarForSalesClient(CarPostDTO carPostDTO, String id){
        restTemplate.put(POSTS_STORE_SERVICE_URI+"/car/"+id,carPostDTO, CarPostDTO.class);
    }

    public void deleteCarForSalesClient(String id){
        restTemplate.delete(POSTS_STORE_SERVICE_URI+"/car/"+ id);
    }

}
