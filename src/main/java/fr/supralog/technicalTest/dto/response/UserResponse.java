package fr.supralog.technicalTest.dto.response;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import fr.supralog.technicalTest.common.enums.Country;
import fr.supralog.technicalTest.common.enums.Gender;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponse(
		Long id,
		String firstName,
		String lastName,
		String email,
		Country country,
		LocalDate birthday,
		Gender gender,
		String address,
		String phone) implements Serializable {

}
