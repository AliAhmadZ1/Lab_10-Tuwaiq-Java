package com.example.lab_10_tuwaiqjavabootcamp.Controller;

import com.example.lab_10_tuwaiqjavabootcamp.ApiResponse.ApiResponse;
import com.example.lab_10_tuwaiqjavabootcamp.Model.User;
import com.example.lab_10_tuwaiqjavabootcamp.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.util.privilegedactions.LoadClass;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        if (userService.getAllUsers().isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("there are no users"));
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        String flag = userService.addUser(user);
        if (flag.equals("username"))
            return ResponseEntity.status(400).body(new ApiResponse("username is already exist"));
        if (flag.equals("email"))
            return ResponseEntity.status(400).body(new ApiResponse("email is already exist"));

        return ResponseEntity.status(200).body(new ApiResponse("new user is added"));
    }

    @PutMapping("/update/{user_id}")
    public ResponseEntity updateUser(@PathVariable Integer user_id,@RequestBody@Valid User user,Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        String flag = userService.updateUser(user_id,user);
        if (flag.equals("username"))
            return ResponseEntity.status(400).body(new ApiResponse("username is already used"));
        if (flag.equals("email"))
            return ResponseEntity.status(400).body(new ApiResponse("email is already used"));
        if (flag.equals("not found"))
            return ResponseEntity.status(400).body(new ApiResponse("this user not found!!"));
        return ResponseEntity.status(200).body(new ApiResponse("user is updated"));
    }

    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity deleteUser(@PathVariable Integer user_id){
        if (userService.deleteUser(user_id))
            return ResponseEntity.status(200).body(new ApiResponse("user is deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("not found?!?!"));
    }

    // endpoint 5
    @GetMapping("/get-date-range/{fDate},{lDate}")
    public ResponseEntity getByRange(@PathVariable LocalDate fDate, @PathVariable LocalDate lDate){
        return ResponseEntity.status(200).body(userService.getUserByRangeDate(fDate, lDate));
    }

    // endpoint 6
    @GetMapping("/get-domain/{domain}")
    public ResponseEntity getByDomain(@PathVariable String domain){
        return ResponseEntity.status(200).body(userService.getBySpecificDomain(domain));
    }

}
