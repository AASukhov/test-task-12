package test_task.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import test_task.repository.UserRepository;
import test_task.service.CustomUserDetailsService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustomUserDetailsServiceTest {

    @InjectMocks
    private CustomUserDetailsService service;

    @Mock
    private UserRepository userRepository;

    public static final String USERNAME = "Alex";
    public static final String PASSWORD = "0000";
    public static final test_task.entity.User USER_1 = new test_task.entity.User(USERNAME,PASSWORD);


    public static final UserDetails USER_DETAILS = User.builder()
            .username(USERNAME)
            .password(PASSWORD)
            .authorities(new ArrayList<>())
            .build();

    @BeforeEach
    void setUp() {
        Mockito.when(userRepository.findUserByUsername(USERNAME)).thenReturn((USER_1));
    }

    @Test
    void loadUserByUsername() {
        assertEquals(USER_DETAILS, service.loadUserByUsername(USERNAME));
    }
}