package com.practice.controller;


import com.practice.dto.PetRequestDto;
import com.practice.response.Response;
import com.practice.service.PetService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/pets")
@ApiResponses({ @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 400, message = "Bad request. Validation failure."),
        @ApiResponse(code = 404, message = "API not found"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal Server Error") })
public class PetController {

    Logger logger = LoggerFactory.getLogger(PetController.class);

    @Autowired
    PetService petService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Get All Pets listed with search and pagination",
            notes = "Get All Pets listed with search and pagination")
    public ResponseEntity<Response> getPetDetails(
        @RequestParam(required = false, name = "petName") final String petName,
        @RequestParam(required = false, name = "place") final String place,
        @RequestParam(required = false, name = "age") final Integer age,
        @RequestParam(required = true, defaultValue = "1", name = "pageNumber") Integer pageNumber,
        @RequestParam(required = true, defaultValue = "5", name = "pageSize") Integer pageSize
        ) {
        logger.debug("Get All Pets listed with search and pagination");
        return ResponseEntity.ok(petService.getAllPetDetails(petName, place, age,
                PageRequest.of((pageNumber > 0 ? --pageNumber : pageNumber), pageSize)
        ));
    }

    @GetMapping(value = "/my-pets/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Get my Pets listed with pagination",
            notes = "Get my Pets listed with pagination")
    public ResponseEntity<Object> getMyPetDetails(
        @PathVariable(required = true, name = "userId") final Long userId,
        @RequestParam(required = true, defaultValue = "1", name = "pageNumber") Integer pageNumber,
        @RequestParam(required = true, defaultValue = "5", name = "pageSize") Integer pageSize
    ) {
        logger.debug("Get my Pets listed with pagination");
        return ResponseEntity.ok(petService.getMyPets(userId,
                PageRequest.of((pageNumber > 0 ? --pageNumber : pageNumber), pageSize)));
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Admin: Add a new Pet",
            notes = "Admin: Add a new Pet")
    public ResponseEntity<Object> getPetDetails(@Valid @RequestBody PetRequestDto petRequestDto) {
        logger.debug("Add a new Pet");
        return ResponseEntity.ok(petService.addNewPet(petRequestDto));
    }

    @PutMapping(value = "/buy/user/{userId}/pet/{petId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Admin: Add a new Pet",
            notes = "Admin: Add a new Pet")
    public ResponseEntity<Object> getPetDetails(@PathVariable("userId") Long userId,
                                                @PathVariable("petId") Long petId) {
        logger.debug("Buy Pet by User");
        return ResponseEntity.ok(petService.buyPet(userId, petId));
    }
}