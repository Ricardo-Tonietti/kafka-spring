package com.store.car.service.impl;

import com.store.car.dto.CarPostDTO;
import com.store.car.entity.CarPostEntity;
import com.store.car.entity.OwnerPostEntity;
import com.store.car.repository.CarPostRespository;
import com.store.car.repository.OwnerPostRepository;
import com.store.car.service.CarPostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarPostServiceImpl implements CarPostService {

    private final CarPostRespository carPostRespository;
    private final OwnerPostRepository ownerPostRepository;


    public CarPostServiceImpl(CarPostRespository carPostRespository, OwnerPostRepository ownerPostRepository) {
        this.carPostRespository = carPostRespository;
        this.ownerPostRepository = ownerPostRepository;
    }

    @Override
    public void newPostDetails(CarPostDTO carPostDTO) {

        CarPostEntity carPostEntity = mapCarDtoToEntity(carPostDTO);
        carPostRespository.save(carPostEntity);

    }

    @Override
    public List<CarPostDTO> getCarSales() {
        List<CarPostDTO> listCarsSales = new ArrayList<>();
        carPostRespository.findAll().forEach(item->{
            listCarsSales.add(mapCarEntityToDto(item));
        });
        return listCarsSales;
    }

    @Override
    public void changeCarSale(CarPostDTO carPostDTO, Long postId) {

        carPostRespository.findById(postId).ifPresentOrElse(item->{
            item.setDescription(carPostDTO.getDescription());
            item.setContact(carPostDTO.getContact());
            item.setCity(carPostDTO.getCity());
            item.setPrice(carPostDTO.getPrice());
            item.setBrand(carPostDTO.getBrand());
            item.setEngineVersion(carPostDTO.getEngineVersion());
            item.setModel(carPostDTO.getModel());

            carPostRespository.save(item);

        }, ()->{
            throw new NoSuchElementException();
        });

    }

    @Override
    public void removeCarSale(Long postId) {
        carPostRespository.deleteById(postId);

    }

    private CarPostDTO mapCarEntityToDto(CarPostEntity carPostEntity){
        return CarPostDTO.builder()
                .brand(carPostEntity.getBrand())
                .city(carPostEntity.getCity())
                .model(carPostEntity.getModel())
                .description(carPostEntity.getDescription())
                .engineVersion(carPostEntity.getEngineVersion())
                .ownerName(carPostEntity.getOwnerPost().getName())
                .price(carPostEntity.getPrice())
                .build();
    }

    private CarPostEntity mapCarDtoToEntity(CarPostDTO carPostDTO) {
        CarPostEntity carPostEntity = new CarPostEntity();
        ownerPostRepository.findById(carPostDTO.getOwnerId()).ifPresentOrElse(item->{
            carPostEntity.setOwnerPost(item);
            carPostEntity.setContact(item.getContactNumber());
        }, ()->{
            throw new RuntimeException();
        });

        carPostEntity.setModel(carPostDTO.getModel());
        carPostEntity.setBrand(carPostDTO.getBrand());
        carPostEntity.setPrice(carPostDTO.getPrice());
        carPostEntity.setDescription(carPostDTO.getDescription());
        carPostEntity.setEngineVersion(carPostDTO.getEngineVersion());
        carPostEntity.setCreatedDate(String.valueOf(new Date()));

        return carPostEntity;
    }
}
