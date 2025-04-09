package com.example.lab_10_tuwaiqjavabootcamp.Repository;

import com.example.lab_10_tuwaiqjavabootcamp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer user_id);

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    @Query("select u from User u where u.registration_date>=?1 and u.registration_date<=?2")
    List<User> getUserByRegistration_datePeriod(LocalDate fDate, LocalDate lDate);
}
