package com.practice.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.dto.PetRequestDto;
import com.practice.entity.Pet;


@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

	void save(PetRequestDto pet);

	Page<Pet> findPetBySearch(String petName, String place, Integer age, Pageable pageable);

	Page<Pet> findByOwnerId(Long ownerId, Pageable pageable);

	Optional<Pet> findById(Long petId);

}
