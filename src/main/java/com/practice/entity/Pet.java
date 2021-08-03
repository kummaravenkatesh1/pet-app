package com.practice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "place")
    private String place;
    @Column(name = "age")
    private Integer age;
    @Column(name = "owner_id")
    private Long ownerId;
    @Column(name = "buy_date")
    private Date buyDate;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "modified_date")
    private Date modifiedDate;
	
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
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long i) {
		this.ownerId = i;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public Pet() {
		super();
		this.id = id;
		this.name = name;
		this.place = place;
		this.age = age;
		this.ownerId = ownerId;
		this.buyDate = buyDate;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}
	
	public Pet(Integer id2, String name2, String place2, Integer age2, Integer ownerId2, Date buyDate2,
			Date createdDate2, Date modifiedDate2) {
		// TODO Auto-generated constructor stub
	}
	public Pet(Long id2, String name2, String place2, Integer age2, Long ownerId2, Date buyDate2, Date createdDate2,
			Date modifiedDate2) {
		// TODO Auto-generated constructor stub
	}
	public void setOwnerId(int i) {
		// TODO Auto-generated method stub
		
	}

	public void add(Pet pet) {
		// TODO Auto-generated method stub
		
	}
    
}
