package com.practice.service.impl;


import com.practice.DateUtils;
import com.practice.constants.CommonConstants;
import com.practice.dto.MyPetResponseDto;
import com.practice.dto.PetRequestDto;
import com.practice.dto.PetResponseDto;
import com.practice.entity.Pet;
import com.practice.entity.User;
import com.practice.entity.UserRoleEnum;
import com.practice.repository.PetRepository;
import com.practice.repository.UserRepository;
import com.practice.response.DataResponse;
import com.practice.response.Response;
import com.practice.service.PetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {

    Logger logger = LoggerFactory.getLogger(PetServiceImpl.class);

    @Autowired
    PetRepository petRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Response getAllPetDetails(final String petName, final String place, final Integer age, Pageable pageable) {
        logger.debug("get All Pet details for all users");
        if(age!=null && (age <= 0 || age > 99)) {
            return new Response(HttpStatus.BAD_REQUEST, CommonConstants.INVALID_AGE);
        }
        Page<Pet> pets = null;
        if(petName==null && place==null && age==null) {
            pets = petRepository.findAll(pageable);
        } else {
            pets = petRepository.findPetBySearch(petName, place, age, pageable);
        }

        if(pets.isEmpty()) {
            return new DataResponse(HttpStatus.NO_CONTENT, CommonConstants.NO_RECORD_FOUND, new ArrayList<PetResponseDto>());
        }
        List<PetResponseDto> petDetails = pets.stream().map(pet -> {
            return new PetResponseDto(
                    pet.getId(),
                    pet.getName(),
                    pet.getPlace(),
                    pet.getAge(),
                    pet.getBuyDate()!=null? Boolean.TRUE: Boolean.FALSE,
                    pet.getBuyDate()!=null? DateUtils.convertToEpochSecond(pet.getBuyDate()): 0L
                    );
        }).collect(Collectors.toList());
        return new DataResponse(HttpStatus.OK, CommonConstants.SUCCESS, petDetails);
    }

    @Override
    public DataResponse getMyPets(Long ownerId, Pageable pageable) {
        logger.debug("get All Pet details for all users");

        Page<Pet> pets = petRepository.findByOwnerId(ownerId, pageable);
        if(pets.isEmpty()) {
            return new DataResponse(HttpStatus.NO_CONTENT, CommonConstants.NO_RECORD_FOUND, new ArrayList<MyPetResponseDto>());
        }
        List<MyPetResponseDto> petDetails = pets.stream().map(pet -> {
            MyPetResponseDto responseDto = new MyPetResponseDto(
                    pet.getId(),
                    pet.getName(),
                    pet.getPlace(),
                    pet.getAge(),
                    pet.getBuyDate()!=null? DateUtils.convertToEpochSecond(pet.getBuyDate()): 0L
            );
            return responseDto;
        }).collect(Collectors.toList());
        return new DataResponse(HttpStatus.OK, CommonConstants.SUCCESS, petDetails);
    }

    @Override
    public Response addNewPet(PetRequestDto petRequestDto) {
        return null;
    }

    @Override
    public Response buyPet(Long userId, Long petId) {

        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent()) {
            return new Response(HttpStatus.BAD_REQUEST, CommonConstants.USER_NOT_FOUND);
        }
        if(!UserRoleEnum.USER.getRole().equals(optionalUser.get().getUserRole().getRole())) {
            return new Response(HttpStatus.UNAUTHORIZED, CommonConstants.UNAUTHORIZED_OPERATION);
        }

        Optional<Pet> optionalPet = petRepository.findById(petId);
        if(!optionalPet.isPresent()) {
            return new Response(HttpStatus.BAD_REQUEST, CommonConstants.PET_NOT_FOUND);
        }
        Pet pet = optionalPet.get();

        if(pet.getBuyDate()!=null) {
            return new Response(HttpStatus.BAD_REQUEST, CommonConstants.CANNOT_BUY_PET);
        }

        pet.setBuyDate(new Date());
        pet.setOwnerId(optionalUser.get().getId());
        pet.setModifiedDate(new Date());
        petRepository.save(pet);

        logger.debug("pet: " + pet.getName()+ " ownership changed to the user: " + optionalUser.get().getName());
        return new Response(HttpStatus.OK, CommonConstants.BUY_PET_SUCCESS);
    }

}