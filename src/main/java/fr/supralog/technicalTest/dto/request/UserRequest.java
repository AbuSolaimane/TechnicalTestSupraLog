package fr.supralog.technicalTest.dto.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import fr.supralog.technicalTest.common.enums.Country;
import fr.supralog.technicalTest.common.enums.Gender;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserRequest (
		String firstName,
		String lastName,
		String email,
		String password,
		String confirmedPassword,
		Country country,
		LocalDate birthday,
		Gender gender,
		String address,
		String phone )  {
	
}
