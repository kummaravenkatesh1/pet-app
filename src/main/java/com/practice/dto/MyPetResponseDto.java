package com.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyPetResponseDto {

  
	private Long id;
    private String name;
    private String place;
    private Integer age;
    private Long boughtDate;
	public MyPetResponseDto(Long id, String name, String place, Integer age, Long boughtDate) {
		super();
		this.id = id;
		this.name = name;
		this.place = place;
		this.age = age;
		this.boughtDate = boughtDate;
	}
    
}
