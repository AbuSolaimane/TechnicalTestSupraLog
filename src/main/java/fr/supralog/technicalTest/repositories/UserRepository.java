package fr.supralog.technicalTest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.supralog.technicalTest.domain.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
