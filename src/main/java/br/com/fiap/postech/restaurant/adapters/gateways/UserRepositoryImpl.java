package br.com.fiap.postech.restaurant.adapters.gateways;

import br.com.fiap.postech.restaurant.adapters.persistense.JpaUserRepository;
import br.com.fiap.postech.restaurant.adapters.persistense.UserData;
import br.com.fiap.postech.restaurant.application.gateway.UserRepository;
import br.com.fiap.postech.restaurant.domain.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final ModelMapper modelMapper;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository, ModelMapper modelMapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User save(User user) {
        UserData userData;
        userData = modelMapper.map(user, UserData.class);
        userData = jpaUserRepository.save(userData);
        return modelMapper.map(userData, User.class);
    }

    @Override
    public User findById(Long id) {
        UserData userData = jpaUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        return modelMapper.map(userData, User.class);
    }

    @Override
    public User findByEmail(String email) {
        UserData userData = jpaUserRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
        return modelMapper.map(userData, User.class);
    }

    @Override
    public List<User> searchUsers(String searchTerm) {
        List<UserData> users = jpaUserRepository.findByNameContainingOrEmailContaining(searchTerm, searchTerm);
        return users.stream()
                .map(userData -> modelMapper.map(userData, User.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        jpaUserRepository.deleteById(id);
    }

}