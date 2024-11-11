package edu.miu.cse.restfuldemo.service;

import edu.miu.cse.restfuldemo.dto.request.UserRequestDto;
import edu.miu.cse.restfuldemo.dto.response.UserResponseDto;
import edu.miu.cse.restfuldemo.exception.user.UserNotFoundException;
import edu.miu.cse.restfuldemo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserResponseDto> createUser(UserRequestDto userRequestDto);
    Optional<UserResponseDto> findByUsername(String username);
    List<UserResponseDto> findAllUsers();
    Optional<UserResponseDto> updateUser(String username, UserRequestDto userRequestDto);
    Optional<UserResponseDto> updateUserPartially(String username, UserRequestDto userRequestDto);
    void deleteUserByUsername(String username);
}
