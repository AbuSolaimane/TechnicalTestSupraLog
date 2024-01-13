package fr.supralog.technicalTest.dto.request;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import fr.supralog.technicalTest.common.enums.Country;
import fr.supralog.technicalTest.common.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserRequest (
		
		@NotBlank(message = "errors.validation.filed.notblank")
		String firstName,
		@NotBlank(message = "errors.validation.filed.notblank")
		String lastName,
		@NotBlank(message = "errors.validation.filed.notblank")
		@Email(message = "errors.validation.email")
		String email,
		@NotBlank(message = "errors.validation.filed.notblank")
		String password,
		@NotBlank(message = "errors.validation.filed.notblank")
		String confirmedPassword,
		@NotNull(message = "errors.validation.filed.notnull")
		Country country,
		@NotNull(message = "errors.validation.filed.notnull")
		@JsonFormat(pattern = "dd/MM/yyyy")
		LocalDate birthday,
		Gender gender,
		String address,
		String phone ) implements Serializable {
	
}
