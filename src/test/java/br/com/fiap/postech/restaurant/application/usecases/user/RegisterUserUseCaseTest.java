package br.com.fiap.postech.restaurant.application.usecases.user;

import br.com.fiap.postech.restaurant.application.gateway.UserRepository;
import br.com.fiap.postech.restaurant.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RegisterUserUseCase registerUserUseCase;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("Maria Souza");
        user.setEmail("maria@example.com");
        user.setPhone("987654321");
    }

    @Test
    void shouldCreateUserSuccessfully() {
        when(userRepository.save(user)).thenReturn(user);

        User createdUser = registerUserUseCase.create(user);

        assertNotNull(createdUser);
        assertEquals("Maria Souza", createdUser.getName());
        assertEquals("maria@example.com", createdUser.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void shouldThrowExceptionWhenUserIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> registerUserUseCase.create(null));
        assertEquals("Dados do usuário são obrigatórios", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUserNameIsNull() {
        user.setName(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> registerUserUseCase.create(user));
        assertEquals("Nome do usuário é obrigatório", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUserNameIsEmpty() {
        user.setName(" ");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> registerUserUseCase.create(user));
        assertEquals("Nome do usuário é obrigatório", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull() {
        user.setEmail(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> registerUserUseCase.create(user));
        assertEquals("Email do usuário é obrigatório", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsEmpty() {
        user.setEmail(" ");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> registerUserUseCase.create(user));
        assertEquals("Email do usuário é obrigatório", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsInvalid() {
        user.setEmail("invalid-email");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> registerUserUseCase.create(user));
        assertEquals("Formato de email inválido", exception.getMessage());
    }
}
