package br.com.fiap.postech.restaurant.adapters.controller;

import br.com.fiap.postech.restaurant.adapters.controller.dto.UserDTO;
import br.com.fiap.postech.restaurant.application.usecases.user.RegisterUserUseCase;
import br.com.fiap.postech.restaurant.domain.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final ModelMapper modelMapper;

    public UserController(RegisterUserUseCase registerUserUseCase, ModelMapper modelMapper) {
        this.registerUserUseCase = registerUserUseCase;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        User domain = modelMapper.map(userDTO, User.class);
        User save = registerUserUseCase.create(domain);
        UserDTO response = modelMapper.map(save, UserDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
