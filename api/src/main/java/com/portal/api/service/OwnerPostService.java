package com.portal.api.service;

import com.portal.api.dto.OwnerPostDTO;
import org.springframework.stereotype.Service;

@Service
public interface OwnerPostService {

    OwnerPostDTO createOwnerCar(OwnerPostDTO ownerPostDTO);
}
