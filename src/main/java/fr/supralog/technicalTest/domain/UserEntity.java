package fr.supralog.technicalTest.domain;

import java.time.LocalDate;

import fr.supralog.technicalTest.common.enums.Country;
import jakarta.persistence.Entity;

@Entity
public class UserEntity {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Country country;
	private LocalDate birthday;
	
}
