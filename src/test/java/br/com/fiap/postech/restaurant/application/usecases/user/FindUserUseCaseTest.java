package br.com.fiap.postech.restaurant.application.usecases.user;

import br.com.fiap.postech.restaurant.adapters.controller.dto.UserProfileDTO;
import br.com.fiap.postech.restaurant.application.gateway.UserRepository;
import br.com.fiap.postech.restaurant.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FindUserUseCase findUserUseCase;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("João Silva");
        user.setEmail("joao@example.com");
        user.setPhone("123456789");
    }

    @Test
    void shouldFindUserById() {
        when(userRepository.findById(1L)).thenReturn(user);

        User foundUser = findUserUseCase.findById(1L);

        assertNotNull(foundUser);
        assertEquals(1L, foundUser.getId());
        assertEquals("João Silva", foundUser.getName());
    }

    @Test
    void shouldThrowExceptionWhenUserIdIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> findUserUseCase.findById(null));
        assertEquals("ID do usuário é obrigatório", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUserNotFoundById() {
        when(userRepository.findById(2L)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> findUserUseCase.findById(2L));
        assertEquals("Usuário não encontrado com o ID: 2", exception.getMessage());
    }

    @Test
    void shouldFindUserByEmail() {
        when(userRepository.findByEmail("joao@example.com")).thenReturn(user);

        User foundUser = findUserUseCase.findByEmail("joao@example.com");

        assertNotNull(foundUser);
        assertEquals("joao@example.com", foundUser.getEmail());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> findUserUseCase.findByEmail(null));
        assertEquals("Email do usuário é obrigatório", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUserNotFoundByEmail() {
        when(userRepository.findByEmail("notfound@example.com")).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> findUserUseCase.findByEmail("notfound@example.com"));
        assertEquals("Usuário não encontrado com o email: notfound@example.com", exception.getMessage());
    }

    @Test
    void shouldSearchUsers() {
        when(userRepository.searchUsers("João"))
                .thenReturn(List.of(user));

        List<User> users = findUserUseCase.searchUsers("João");

        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        assertEquals("João Silva", users.get(0).getName());
    }

    @Test
    void shouldThrowExceptionWhenSearchTermIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> findUserUseCase.searchUsers(""));
        assertEquals("Termo de busca é obrigatório", exception.getMessage());
    }

    @Test
    void shouldGetUserProfile() {
        when(userRepository.findById(1L)).thenReturn(user);

        UserProfileDTO profile = findUserUseCase.getUserProfile(1L);

        assertNotNull(profile);
        assertEquals(1L, profile.getId());
        assertEquals("João Silva", profile.getName());
        assertEquals("joao@example.com", profile.getEmail());
    }
}
