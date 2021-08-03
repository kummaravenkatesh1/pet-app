package com.practice.service;

import com.practice.dto.PetRequestDto;
import com.practice.response.Response;

import org.springframework.data.domain.Pageable;

public interface PetService {

    Response getAllPetDetails(String petName, String place, Integer age, Pageable pageable);

    Response getMyPets(Long userId, Pageable pageable);

    Response addNewPet(PetRequestDto petRequestDto);

    Response buyPet(Long userId, Long petId);
}
