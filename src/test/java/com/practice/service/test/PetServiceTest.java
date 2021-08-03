package com.practice.service.test;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.practice.DateUtils;
import com.practice.constants.CommonConstants;
import com.practice.dto.PetResponseDto;
import com.practice.entity.Pet;
import com.practice.repository.PetRepository;
import com.practice.response.DataResponse;
import com.practice.response.Response;
import com.practice.service.impl.PetServiceImpl;

@ExtendWith(SpringExtension.class)
public class PetServiceTest {

    @InjectMocks
    private PetServiceImpl petService;

    @Mock
    private PetRepository petRepository;

    @Test
    public void testGetAllPetDetailsWithoutParameters() {

        List<Pet> pets = new ArrayList<>();
        Pet pet = createPet(1L, "Scooby", "Mumbai", 2, 1L, null, new Date(), new Date());
        pets.add(pet);

        Page<Pet> petPage = new PageImpl<>(pets);
        Pageable pageable = PageRequest.of(0, 1);
        Mockito.when(petRepository.findAll(pageable)).thenReturn(petPage);

        Response response = petService.getAllPetDetails(null, null, null, pageable);

        List<PetResponseDto> petDetails = new ArrayList<>();
        petDetails.add(createPetResponseDto(pet));

        DataResponse expectedResponse = new DataResponse(HttpStatus.OK, CommonConstants.SUCCESS, petDetails);
        Assertions.assertEquals(1, ((List<PetResponseDto>)((DataResponse)response).getData()).size());
        Assertions.assertEquals(expectedResponse.getResponseCode(), ((DataResponse)response).getResponseCode());
    }

    @Test
    public void testGetAllPetDetailsWithParameters() {
        List<Pet> pets = new ArrayList<>();
        Pet pet = createPet(1L, "Scooby", "Mumbai", 2, 1L, null, new Date(), new Date());
        pets.add(pet);

        Page<Pet> petPage = new PageImpl<>(pets);
        Pageable pageable = PageRequest.of(0, 1);
        Mockito.when(petRepository.findPetBySearch("Scooby",null, null, pageable)).thenReturn(petPage);

        Response response = petService.getAllPetDetails("Scooby", null, null, pageable);

        List<PetResponseDto> petDetails = new ArrayList<>();
        petDetails.add(createPetResponseDto(pet));

        DataResponse expectedResponse = new DataResponse(HttpStatus.OK, CommonConstants.SUCCESS, petDetails);
        Assertions.assertEquals(1, ((List<PetResponseDto>)((DataResponse)response).getData()).size());
        Assertions.assertEquals(expectedResponse.getResponseCode(), ((DataResponse)response).getResponseCode());
    }

    @Test
    public void testGetMyPetsWithData() {
        List<Pet> pets = new ArrayList<>();
        Pet pet = createPet(1L, "Scooby", "Mumbai", 2, 2L, null, new Date(), new Date());
        pets.add(pet);

        Page<Pet> petPage = new PageImpl<>(pets);
        Pageable pageable = PageRequest.of(0, 1);

        Long ownerId = 2L;

        Mockito.when(petRepository.findByOwnerId(ownerId, pageable)).thenReturn(petPage);
        DataResponse response = petService.getMyPets(ownerId, pageable);

        List<PetResponseDto> petDetails = new ArrayList<>();
        petDetails.add(createPetResponseDto(pet));

        DataResponse expectedResponse = new DataResponse(HttpStatus.OK, CommonConstants.SUCCESS, petDetails);
        Assertions.assertEquals(1, ((List<PetResponseDto>)response.getData()).size());
        Assertions.assertEquals(expectedResponse.getResponseCode(), response.getResponseCode());
    }

    @Test
    public void testGetMyPetsNoData() {
        List<Pet> pets = new ArrayList<>();
        Page<Pet> petPage = new PageImpl<>(pets);
        Pageable pageable = PageRequest.of(0, 1);

        Long ownerId = 2L;

        Mockito.when(petRepository.findByOwnerId(ownerId, pageable)).thenReturn(petPage);
        DataResponse response = petService.getMyPets(ownerId, pageable);

        List<PetResponseDto> petDetails = new ArrayList<>();

        DataResponse expectedResponse = new DataResponse(HttpStatus.NO_CONTENT, CommonConstants.NO_RECORD_FOUND, petDetails);
        //Assertions.assertEquals(1, ((List<PetResponseDto>)response.getData()).size());
        Assertions.assertEquals(expectedResponse.getResponseCode(), response.getResponseCode());
    }

    private PetResponseDto createPetResponseDto(Pet pet) {
        return new PetResponseDto(
                pet.getId(),
                pet.getName(),
                pet.getPlace(),
                pet.getAge(),
                pet.getBuyDate()!=null? Boolean.TRUE: Boolean.FALSE,
                pet.getBuyDate()!=null? DateUtils.convertToEpochSecond(pet.getBuyDate()): 0L
        );
    }

    private Pet createPet(Long id, String name, String place, Integer age, Long ownerId, Date buyDate,
                               Date createdDate, Date modifiedDate) {
        return new Pet(id, name, place, age, ownerId, buyDate, createdDate, modifiedDate);
    }

}