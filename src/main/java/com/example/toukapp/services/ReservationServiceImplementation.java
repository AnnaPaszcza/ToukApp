package com.example.toukapp.services;

import com.example.toukapp.dtos.*;
import com.example.toukapp.dtos.ReservationResponse;
import com.example.toukapp.entity.*;
import com.example.toukapp.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ReservationServiceImplementation implements ReservationService{
    private final ReservationRepository reservationRepository;
    private final ScreeningService screeningService;
    private final SeatService seatService;
    private final TicketService ticketService;
    private final TicketTypeService ticketTypeService;

    @Autowired
    public ReservationServiceImplementation(ReservationRepository reservationRepository, ScreeningService screeningService, SeatService seatService, TicketService ticketService, TicketTypeService ticketTypeService) {
        this.reservationRepository = reservationRepository;
        this.screeningService = screeningService;
        this.seatService = seatService;
        this.ticketService = ticketService;
        this.ticketTypeService = ticketTypeService;
    }

    @Override
    public List<ReservationResponse> getAll() {
        return StreamSupport.stream(reservationRepository.findAll().spliterator(), false)
                .map(reservationEntity -> new ReservationResponse(reservationEntity.getReservationId(), reservationEntity.getName(), reservationEntity.getSurname(), reservationEntity.getTotalPrice(), reservationEntity.getDate(), reservationEntity.getExpirationDate(), reservationEntity.getExpirationTime()))
                .collect(Collectors.toList());
    }

    @Override
    public ReservationResponse getReservation(int reservationId){
        List<ReservationResponse> list = getAll();
        if(reservationRepository.existsById(reservationId)) {
            Optional<ReservationResponse> foundReservation = list.stream().filter(reservation -> reservation.getReservationId() == reservationId).findAny();
            return foundReservation.orElse(null);
        }
        return null;
    }

    @Override
    public void addReservation(ReservationRequest reservationRequest){
        Reservation reservationEntity = new Reservation();
        Float totalPrice = (float) 0;
        if (reservationRequest.getName().length() > 2 && reservationRequest.getSurname().length() > 2 &&
            Character.isUpperCase(reservationRequest.getName().toCharArray()[0]) && Character.isUpperCase(reservationRequest.getSurname().toCharArray()[0])) {
            reservationEntity.setName(reservationRequest.getName());
            reservationEntity.setSurname(reservationRequest.getSurname());
            reservationEntity.setTotalPrice(totalPrice);
            reservationEntity.setDate(reservationRequest.getDate());
            reservationEntity.setExpirationDate(reservationRequest.getExpirationDate());
            reservationEntity.setExpirationTime(reservationRequest.getExpirationTime());
            reservationRepository.save(reservationEntity);
        }
    }

    public Float makeReservation(MakeReservationRequest makeReservationRequest) {
        ScreeningResponse screeningResponse = screeningService.findById(makeReservationRequest.getScreeningId());
        Screening screening = new Screening();
        screening.setScreeningId(screeningResponse.getScreeningId());
        screening.setMovie(screeningResponse.getMovie());
        screening.setTime(screeningResponse.getTime());
        screening.setRoom(screeningResponse.getRoom());
        screening.setDate(screeningResponse.getDate());

        Time expirationTime = Time.valueOf("23:59:59");
        Reservation reservationEntity = new Reservation();
        reservationEntity.setName(makeReservationRequest.getName());
        reservationEntity.setSurname(makeReservationRequest.getSurname());
        reservationEntity.setTotalPrice((float)0.0);
        reservationEntity.setDate(screeningResponse.getDate());
        reservationEntity.setExpirationTime(expirationTime);
        reservationEntity.setExpirationDate(screeningResponse.getDate());
        reservationEntity = reservationRepository.save(reservationEntity);
        Reservation finalReservationEntity = reservationEntity;

        makeReservationRequest.getChosenSeatIds().forEach(seat -> {
            Ticket ticket = new Ticket();
            TicketTypeResponse ticketTypeResponse = ticketTypeService.getTicketType(seat.getValue());
            TicketType ticketType = new TicketType();
            TicketRequest ticketRequest = new TicketRequest();
            ticketType.setName(ticketTypeResponse.getName());
            ticketType.setTypeId(ticketTypeResponse.getTypeId());
            ticketType.setPrice(ticketTypeResponse.getPrice());
            ticketRequest.setReservation(finalReservationEntity);
            ticketRequest.setType(ticketType);
            ticketRequest.setScreening(screening);
            ticketService.addTicket(ticketRequest);

            SeatResponse seatResponse = seatService.getSeat(seat.getKey());
            Seat chosenSeat = new Seat();
            chosenSeat.setSeatId(seatResponse.getSeatId());
            chosenSeat.setRoom(seatResponse.getRoom());
            chosenSeat.setNumber(seatResponse.getNumber());
            chosenSeat.setRow(seatResponse.getRow());
            chosenSeat.setIsTaken(true);
            ticket.setSeat(chosenSeat);

            addTicket(finalReservationEntity.getReservationId(), ticketType.getPrice());
        });
        reservationEntity = reservationRepository.save(reservationEntity);
        return reservationEntity.getTotalPrice();
    }

    @Override
    public void addTicket(int reservationId, Float price) {
        ReservationResponse reservationResponse = this.getReservation(reservationId);
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setReservationId(reservationId);
        reservationRequest.setName(reservationResponse.getName());
        reservationRequest.setSurname(reservationResponse.getSurname());
        reservationRequest.setTotalPrice(reservationResponse.getTotalPrice() + price);
        reservationRequest.setDate(reservationResponse.getDate());
        reservationRequest.setExpirationTime(reservationResponse.getExpirationTime());
        reservationRequest.setExpirationDate(reservationResponse.getExpirationDate());
        this.updateReservation(reservationId, reservationRequest);
    }

    @Override
    public void deleteReservation(int reservationId){
        if(reservationRepository.existsById(reservationId)){
            reservationRepository.deleteById(reservationId);
        }
    }

    @Override
    public void updateReservation(int reservationId, ReservationRequest reservationRequest){
        if(reservationRepository.existsById(reservationId)){
            Reservation reservationEntity = new Reservation();
            reservationEntity.setName(reservationRequest.getName());
            reservationEntity.setSurname(reservationRequest.getSurname());
            reservationEntity.setTotalPrice(reservationRequest.getTotalPrice());
            reservationEntity.setDate(reservationRequest.getDate());
            reservationEntity.setExpirationDate(reservationRequest.getExpirationDate());
            reservationEntity.setExpirationTime(reservationRequest.getExpirationTime());
            reservationRepository.save(reservationEntity);
        }
    }
}
