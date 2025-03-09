package br.com.fiap.postech.restaurant.adapters.controller;

import br.com.fiap.postech.restaurant.adapters.controller.dto.ReservationDTO;
import br.com.fiap.postech.restaurant.application.usecases.reservation.CreateReservationUseCase;
import br.com.fiap.postech.restaurant.domain.entities.Reservation;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final CreateReservationUseCase createReservationUseCase;
    private final ModelMapper modelMapper;

    public ReservationController(CreateReservationUseCase createReservationUseCase,
                                 ModelMapper modelMapper) {
        this.createReservationUseCase = createReservationUseCase;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> newReservation(@Valid @RequestBody ReservationDTO dto){
        Reservation saved = createReservationUseCase.execute(dto);
        dto = createReservationUseCase.convertDomainToDto(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
