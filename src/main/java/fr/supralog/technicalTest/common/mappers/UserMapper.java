package fr.supralog.technicalTest.common.mappers;

import java.util.Objects;

import fr.supralog.technicalTest.domain.UserEntity;
import fr.supralog.technicalTest.dto.request.UserRequest;
import fr.supralog.technicalTest.dto.response.UserResponse;

public class UserMapper {

    private UserMapper() {
    	
    }

    public static UserEntity mapToEntity(UserRequest request) {
        Objects.requireNonNull(request, "User request must not be null");

        UserEntity entity = new UserEntity();
        entity.setFirstName(request.firstName());
        entity.setLastName(request.lastName());
        entity.setEmail(request.email());
        entity.setPassword(request.password());
        entity.setCountry(request.country());
        entity.setBirthday(request.birthday());
        entity.setGender(request.gender());
        entity.setAddress(request.address());
        entity.setPhone(request.phone());

        return entity;
    }

    public static UserResponse mapToDto(UserEntity entity) {
        Objects.requireNonNull(entity, "User must not be null");

        return new UserResponse(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getCountry(),
                entity.getBirthday(),
                entity.getGender(),
                entity.getAddress(),
                entity.getPhone()
        );
    }
}
