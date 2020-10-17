package ir.mahmood.sahame.service;

import ir.mahmood.sahame.dto.UserDto;
import ir.mahmood.sahame.entity.UserEntity;
import ir.mahmood.sahame.exception.MaxRequestExceededException;
import ir.mahmood.sahame.repository.UserRepository;
import ir.mahmood.sahame.util.AppUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private ModelMapper modelMapper;

    private final AppUtil appUtil;
    private final TransportService transportService;

    public JwtUserDetailsService(AppUtil appUtil, TransportService transportService) {
        this.appUtil = appUtil;
        this.transportService = transportService;
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

    public String checkUserLoginStrategy(String username) throws MaxRequestExceededException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            // generate otp code
            String otpCode = appUtil.generateOTPCode();

            // persist username
            userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setCode(otpCode);
            userEntity.setCodeSentAt(new Date());
            userEntity.setCodeSentCount(1);
            userRepository.save(userEntity);

            // send otp sms
            transportService.sendSms(username, "کد ورود: " + otpCode);

            // response to user
            return "first-register";
        } else if (userEntity.getPassword() == null) {
            // check number of otp sms sent
            long timesAgo = System.currentTimeMillis() - userEntity.getCodeSentAt().getTime();
            if (userEntity.getCodeSentCount() > 3 && timesAgo < 24 * 60 * 60 * 1000) {
                throw new MaxRequestExceededException("More than 5 request in last 24 hour");
            }

            // generate otp code
            String otpCode = appUtil.generateOTPCode();

            // persist username
            userEntity.setCode(otpCode);
            userEntity.setCodeSentAt(new Date());
            userEntity.setCodeSentCount(userEntity.getCodeSentCount() + 1);
            userRepository.save(userEntity);

            // send otp sms
            transportService.sendSms(username, "کد ورود: " + otpCode);

            // response to user
            return "second-register";
        } else {
            return "password";
        }

    }

    public UserEntity save(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setPassword(bcryptEncoder.encode(userDto.getPassword()));
        return userRepository.save(userEntity);
    }
}
