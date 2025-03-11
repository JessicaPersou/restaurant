package br.com.fiap.postech.restaurant.adapters.controller;

import br.com.fiap.postech.restaurant.adapters.controller.dto.ReservationDTO;
import br.com.fiap.postech.restaurant.application.usecases.reservation.CreateReservationUseCase;
import br.com.fiap.postech.restaurant.application.usecases.reservation.ListReservationUseCase;
import br.com.fiap.postech.restaurant.application.usecases.reservation.UpdateReservationUseCase;
import br.com.fiap.postech.restaurant.domain.entities.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final CreateReservationUseCase createReservationUseCase;
    private final ListReservationUseCase listReservationUseCase;
    private final UpdateReservationUseCase updateReservationUseCase;
    private final ModelMapper modelMapper;

    public ReservationController(
            CreateReservationUseCase createReservationUseCase,
            ListReservationUseCase listReservationUseCase,
            UpdateReservationUseCase updateReservationUseCase, ModelMapper modelMapper) {
        this.createReservationUseCase = createReservationUseCase;
        this.listReservationUseCase = listReservationUseCase;
        this.updateReservationUseCase = updateReservationUseCase;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = createReservationUseCase.execute(reservationDTO);
        ReservationDTO responseDTO = createReservationUseCase.convertDomainToDto(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservation(@PathVariable Long id) {
        Reservation reservation = listReservationUseCase.findById(id);
        ReservationDTO responseDTO = createReservationUseCase.convertDomainToDto(reservation);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByUser(@PathVariable Long userId) {
        List<Reservation> reservations = listReservationUseCase.findByUserId(userId);
        List<ReservationDTO> responseDTOs = reservations.stream()
                .map(createReservationUseCase::convertDomainToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByRestaurant(@PathVariable Long restaurantId) {
        List<Reservation> reservations = listReservationUseCase.findByRestaurantId(restaurantId);
        List<ReservationDTO> responseDTOs = reservations.stream()
                .map(createReservationUseCase::convertDomainToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

//    @GetMapping("/date-range")
//    public ResponseEntity<List<ReservationDTO>> getReservationsByDateRange(
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
//        List<Reservation> reservations = listReservationUseCase.findByDateRange(start, end);
//        List<ReservationDTO> responseDTOs = reservations.stream()
//                .map(createReservationUseCase::convertDomainToDto)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(responseDTOs);
//    }

    @GetMapping("/restaurant/{restaurantId}/date-range")
    public ResponseEntity<List<ReservationDTO>> getReservationsByDateRangeAndRestaurant(
            @PathVariable Long restaurantId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<Reservation> reservations = listReservationUseCase.findByDateRangeAndRestaurant(start, end, restaurantId);
        List<ReservationDTO> responseDTOs = reservations.stream()
                .map(createReservationUseCase::convertDomainToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<ReservationDTO> updateReservation(
//            @PathVariable Long id,
//            @RequestBody ReservationDTO reservationDTO) {
//        reservationDTO.setId(id);
//        ReservationDTO reservation = updateReservationUseCase.execute(reservationDTO);
//        modelMapper.map(reservation,ReservationDTO.class);
//        Reservation responseDTO = createReservationUseCase.convertDomainToDto(reservation);
//        return ResponseEntity.ok(responseDTO);
//    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        updateReservationUseCase.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }
}
