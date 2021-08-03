/*
 * package com.practice.service.test;
 * 
 * import java.util.ArrayList; import java.util.List; import
 * org.junit.jupiter.api.Test;
 * 
 * import org.junit.jupiter.api.Assertions; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus;
 * 
 * import com.practice.constants.CommonConstants; import
 * com.practice.dto.PetRequestDto; import com.practice.dto.PetResponseDto;
 * import com.practice.entity.Pet; import com.practice.repository.PetRepository;
 * import com.practice.response.DataResponse; import
 * com.practice.service.impl.PetServiceImpl;
 * 
 * public class PetsAddTest {
 * 
 * @Autowired PetServiceImpl petServiceImpl;
 * 
 * @Test private void addPet_Pass() { PetRequestDto petDto = new
 * PetRequestDto(); petDto.setName("pet1"); petDto.setPlace("place1");
 * petDto.setAge(10); DataResponse response = petServiceImpl.addNewPet(petDto);
 * DataResponse expectedResponse = new DataResponse(HttpStatus.OK,
 * CommonConstants.SUCCESS, petDto);
 * Assertions.assertEquals(expectedResponse.getResponseCode(),
 * response.getResponseCode()); }
 * 
 * 
 * }
 */