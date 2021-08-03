package com.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PetResponseDto {

    private Long id;
    private String name;
    private String place;
    private Integer age;
    private Boolean isSold;
    private Long soldDate;
	
		
	public PetResponseDto(Long id, String name, String place, Integer age, Boolean isSold, Long soldDate) {
		super();
		this.id = id;
		this.name = name;
		this.place = place;
		this.age = age;
		this.isSold = isSold;
		this.soldDate = soldDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Boolean getIsSold() {
		return isSold;
	}
	public void setIsSold(Boolean isSold) {
		this.isSold = isSold;
	}
	public Long getSoldDate() {
		return soldDate;
	}
	public void setSoldDate(Long soldDate) {
		this.soldDate = soldDate;
	}
    

}
