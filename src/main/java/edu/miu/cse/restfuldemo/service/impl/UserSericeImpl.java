package edu.miu.cse.restfuldemo.service.impl;

import edu.miu.cse.restfuldemo.dto.request.UserRequestDto;
import edu.miu.cse.restfuldemo.dto.response.UserResponseDto;
import edu.miu.cse.restfuldemo.exception.user.UserNotFoundException;
import edu.miu.cse.restfuldemo.model.User;
import edu.miu.cse.restfuldemo.repository.UserRepository;
import edu.miu.cse.restfuldemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSericeImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<UserResponseDto> createUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setUsername(userRequestDto.username());
        user.setPassword(userRequestDto.password());
        User savedUser = userRepository.save(user);
        UserResponseDto userResponseDto = new UserResponseDto(savedUser.getUsername());
        return Optional.of(userResponseDto);
    }

    @Override
    public Optional<UserResponseDto> findByUsername(String username) {
        Optional<User> foundUser = userRepository.findUserByUsername(username);
        if (foundUser.isPresent()) {
            UserResponseDto userResponseDto = new UserResponseDto(foundUser.get().getUsername());
            return Optional.of(userResponseDto);
        }
        throw new UserNotFoundException(username + " is not found.");
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for (User user : users) {
            UserResponseDto userResponseDto = new UserResponseDto(user.getUsername());
            userResponseDtos.add(userResponseDto);
        }
        return userResponseDtos;
    }

    @Override
    public Optional<UserResponseDto> updateUser(String username, UserRequestDto userRequestDto) {
        //find the user
        Optional<User> foundUser = userRepository.findUserByUsername(username);
        if (foundUser.isPresent()) {
            User user = foundUser.get();
            user.setUsername(userRequestDto.username());
            user.setPassword(userRequestDto.password());
            //save the modified user in db
            User savedUser = userRepository.save(user);
            return Optional.of(new UserResponseDto(savedUser.getUsername()));
        }
        throw new UserNotFoundException(username + " is not found.");
    }

    @Override
    public Optional<UserResponseDto> updateUserPartially(String username, UserRequestDto userRequestDto) {
        Optional<User> foundUser = userRepository.findUserByUsername(username);
        if (foundUser.isPresent()) {
            User user = foundUser.get();
            if (userRequestDto.username() != null) {
                user.setUsername(userRequestDto.username());
            }
            if (userRequestDto.password() != null) {
                user.setPassword(userRequestDto.password());
            }
            User savedUser = userRepository.save(user);
            return Optional.of(new UserResponseDto(savedUser.getUsername()));
        }
        throw new UserNotFoundException(username + " is not found.");
    }
}
