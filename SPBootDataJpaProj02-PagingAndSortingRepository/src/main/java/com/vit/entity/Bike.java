//Bike.java
package com.vit.entity;

import java.io.Serializable; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "SP_DATA_BIKE_DETAILS")
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Bike implements Serializable {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer bId;
		
		@Column(length = 20)
		@NonNull
		private String bName;
		
		@Column(length=20)
		@NonNull
		private String brand;
		
		@NonNull
		private Double rating;
}
