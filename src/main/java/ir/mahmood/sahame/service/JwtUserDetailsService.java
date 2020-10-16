package ir.mahmood.sahame.service;

import ir.mahmood.sahame.dto.UserDto;
import ir.mahmood.sahame.entity.UserEntity;
import ir.mahmood.sahame.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder bcryptEncoder;

    private final ModelMapper modelMapper;

    @Autowired
    public JwtUserDetailsService(UserRepository userRepository, PasswordEncoder bcryptEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPassword(),
                new ArrayList<>());
    }

    public UserEntity save(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setPassword(bcryptEncoder.encode(userDto.getPassword()));
        return userRepository.save(userEntity);
    }
}
