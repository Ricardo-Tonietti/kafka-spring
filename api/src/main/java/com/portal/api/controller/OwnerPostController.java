package com.portal.api.controller;

import com.portal.api.dto.OwnerPostDTO;
import com.portal.api.service.OwnerPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner")
public class OwnerPostController {

    private final Logger LOG = (Logger) LoggerFactory.getLogger(OwnerPostController.class);
    private final OwnerPostService ownerPostService;

    public OwnerPostController(OwnerPostService ownerPostService) {
        this.ownerPostService = ownerPostService;
    }

    @PostMapping
    public ResponseEntity<OwnerPostDTO> createOwnerCar(@RequestBody OwnerPostDTO ownerPostDTO){

        LOG.info("USING API REST - CREATED NEW USER : {}", ownerPostDTO);
        ownerPostService.createOwnerCar(ownerPostDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ownerPostService.createOwnerCar(ownerPostDTO));
    }
}
