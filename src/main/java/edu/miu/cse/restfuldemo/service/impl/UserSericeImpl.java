package edu.miu.cse.restfuldemo.service.impl;

import edu.miu.cse.restfuldemo.dto.request.UserRequestDto;
import edu.miu.cse.restfuldemo.dto.response.UserResponseDto;
import edu.miu.cse.restfuldemo.exception.user.UserNotFoundException;
import edu.miu.cse.restfuldemo.model.User;
import edu.miu.cse.restfuldemo.repository.UserRepository;
import edu.miu.cse.restfuldemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Optional<UserResponseDto> findByUsername(String username) throws UserNotFoundException {
//        Optional<User> foundUser = userRepository.findUserByUsername(username);
//        if (foundUser.isPresent()) {
//            UserResponseDto userResponseDto = new UserResponseDto(foundUser.get().getUsername());
//            return Optional.of(userResponseDto);
//        }
        throw new UserNotFoundException(username + " is not found.");
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        return List.of();
    }
}
