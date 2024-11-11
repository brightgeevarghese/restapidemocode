package edu.miu.cse.restfuldemo.controller;

import edu.miu.cse.restfuldemo.dto.request.UserRequestDto;
import edu.miu.cse.restfuldemo.dto.response.UserResponseDto;
import edu.miu.cse.restfuldemo.model.User;
import edu.miu.cse.restfuldemo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/{username}")
    public ResponseEntity<UserResponseDto> findUserByName(@PathVariable String username) {
        Optional<UserResponseDto> userResponseDto = userService.findByUsername(username);
//        if (userResponseDto.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userResponseDto.get());
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUsers() {
        List<UserResponseDto> userResponseDtos = userService.findAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDtos);
    }

    @PutMapping("/{username}")//api/v1/users/username
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable String username,
            @RequestBody @Valid UserRequestDto userRequestDto
    ) {
        Optional<UserResponseDto> userResponseDto = userService.updateUser(username, userRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto.get());
    }

    @PatchMapping("/{username}")//api/v1/users/username
    public ResponseEntity<UserResponseDto> updateUserPartially(
            @PathVariable String username,
            @RequestBody UserRequestDto userRequestDto
    ) {
        Optional<UserResponseDto> userResponseDto = userService.updateUserPartially(username, userRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto.get());
    }
}
