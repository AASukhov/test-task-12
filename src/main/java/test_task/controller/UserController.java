package test_task.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test_task.dto.TokenDTO;
import test_task.dto.UserDTO;
import test_task.service.UserService;

@RestController
@RequestMapping()
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login (@RequestBody UserDTO requestDTO) {
        return service.login(requestDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody UserDTO requestDTO) {
        return service.register(requestDTO);
    }
}
