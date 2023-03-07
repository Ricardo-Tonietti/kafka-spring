package com.portal.api.service.impl;

import com.portal.api.client.CarPostStoreClient;
import com.portal.api.dto.OwnerPostDTO;
import com.portal.api.service.OwnerPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerPostServiceImpl implements OwnerPostService {

    @Autowired
    private CarPostStoreClient carPostStoreClient;
    @Override
    public OwnerPostDTO createOwnerCar(OwnerPostDTO ownerPostDTO) {
        carPostStoreClient.ownerPostClient(ownerPostDTO);
        return ownerPostDTO;
    }
}
