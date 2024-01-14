package fr.supralog.technicalTest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.supralog.technicalTest.common.enums.Country;
import fr.supralog.technicalTest.domain.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmail(String username);
	List<UserEntity> findByCountry(Country country);
}
