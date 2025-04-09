package com.example.lab_10_tuwaiqjavabootcamp.Service;

import com.example.lab_10_tuwaiqjavabootcamp.Model.User;
import com.example.lab_10_tuwaiqjavabootcamp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String addUser(User user) {

        User usernameCheck = userRepository.findUserByUsername(user.getUsername());
        User emailCheck = userRepository.findUserByEmail(user.getEmail());

        if (usernameCheck != null)
            return "username";
        if (emailCheck != null)
            return "email";

        user.setRegistration_date(LocalDate.now());
        userRepository.save(user);
        return "saved";
    }

    public String updateUser(Integer user_id, User user) {

        User oldUser = userRepository.findUserById(user_id);
        User usernameCheck = userRepository.findUserByUsername(user.getUsername());
        User emailCheck = userRepository.findUserByEmail(user.getEmail());

        if (oldUser == null)
            return "not found";
        if (usernameCheck != null && usernameCheck.getId() != oldUser.getId())
            return "username";
        if (emailCheck != null && emailCheck.getId() != oldUser.getId())
            return "email";

        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setUsername(user.getUsername());
        userRepository.save(oldUser);
        return "updated";

    }

    public Boolean deleteUser(Integer user_id) {
        User user = userRepository.findUserById(user_id);

        if (user == null)
            return false;

        userRepository.delete(user);
        return true;
    }

    // 5
    public List<User> getUserByRangeDate(LocalDate fDate,LocalDate lDate){
        return userRepository.getUserByRegistration_datePeriod(fDate, lDate);
    }

}
