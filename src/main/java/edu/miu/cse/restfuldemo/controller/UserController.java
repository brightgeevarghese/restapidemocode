package edu.miu.cse.restfuldemo.controller;

import edu.miu.cse.restfuldemo.dto.request.UserRequestDto;
import edu.miu.cse.restfuldemo.dto.response.UserResponseDto;
import edu.miu.cse.restfuldemo.model.User;
import edu.miu.cse.restfuldemo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
//Set base url mapping
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(
            @Valid
            @RequestBody
            UserRequestDto userRequestDto
    ) {
        Optional<UserResponseDto> userResponseDto = userService.createUser(userRequestDto);
        if (userResponseDto.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    userResponseDto.get()//UserResponseDto
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
