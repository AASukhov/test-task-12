package test_task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import test_task.dto.TokenDTO;
import test_task.dto.UserDTO;
import test_task.entity.User;
import test_task.repository.UserRepository;
import test_task.security.AuthTokenGenerator;

import java.util.List;

@Service
public class UserService {

    private AuthenticationManager manager;
    private AuthTokenGenerator generator;
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public UserService(AuthenticationManager manager,
                       AuthTokenGenerator generator,
                       UserRepository userRepository) {
        this.manager = manager;
        this.generator = generator;
        this.userRepository = userRepository;
    }

    public ResponseEntity<TokenDTO> login(@RequestBody UserDTO requestDTO) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = generator.generate(authentication);
        TokenDTO response = new TokenDTO(token);
        if (response.getToken() == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<String> register(@RequestBody UserDTO requestDTO) {
        if (userRepository.existsByUsername(requestDTO.getUsername())) {
            return new ResponseEntity<>("The name has been taken", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setPassword(encoder.encode(requestDTO.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
