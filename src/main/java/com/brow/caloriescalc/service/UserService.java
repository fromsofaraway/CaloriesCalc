package com.brow.caloriescalc.service;

import com.brow.caloriescalc.dto.AuthDto;
import com.brow.caloriescalc.dto.UserDto;
import com.brow.caloriescalc.exception.ResourceNotFoundException;
import com.brow.caloriescalc.model.Role;
import com.brow.caloriescalc.model.RoleEnum;
import com.brow.caloriescalc.model.User;
import com.brow.caloriescalc.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service class for managing user-related operations.
 */
@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private RoleService roleService;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User createUser(AuthDto authDto) {
        User user = new User();
        Role role = roleService.getRoleByName(RoleEnum.ROLE_USER)
                .orElseThrow(() -> new ResourceNotFoundException("ROLE_USER not found"));
        ZoneId zoneId = ZoneId.of("UTC"); // default hardcode
        String username = authDto.getUsername();
        String rawPassword = authDto.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);

        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setRole(role);
        user.setTimezone(zoneId);

        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserDto> userDtos = users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());

        return userDtos;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public ZonedDateTime getStartOfDay(Long userId) {
        User user = getUserById(userId);
        ZoneId userZone = user.getTimezone();
        return ZonedDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT, userZone);
    }

    public ZonedDateTime getEndOfDay(Long userId) {
        User user = getUserById(userId);
        ZoneId userZone = user.getTimezone();
        return ZonedDateTime.of(LocalDate.now(), LocalTime.MAX, userZone);
    }

    public Set<String> getAvailableZoneIds() {
        return ZoneId.getAvailableZoneIds();
    }
}
