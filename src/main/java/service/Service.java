package service;

import domain.*;
import repository.interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private EmployeeRepository employeeRepository;
    private ReservationRepository reservationRepository;
    private ShowRepository showRepository;
    private SpectatorRepository spectatorRepository;
    private SeatRepository seatRepository;

    public Service(EmployeeRepository employeeRepository, ReservationRepository reservationRepository, ShowRepository showRepository, SpectatorRepository spectatorRepository, SeatRepository seatRepository) {
        this.employeeRepository = employeeRepository;
        this.reservationRepository = reservationRepository;
        this.showRepository = showRepository;
        this.spectatorRepository = spectatorRepository;
        this.seatRepository = seatRepository;
    }

    public List<Show> getShows(){
        List<Show> list = new ArrayList<>();
        //Iterator<Show> iterator = null;
        Iterable<Show> iterator = showRepository.findAll();
        iterator.forEach(list::add);
        return list;
    }

    public List<Seat> getSeatsList(Show show){
        List<Seat> seats = new ArrayList<>();
        Iterable<Seat> iterable = seatRepository.getFreeSeats(show);
        iterable.forEach(seats::add);
        seats.forEach(x->x.setShow(show));
        return seats;
    }

    public void saveReservation(Seat seat, Spectator spectator){
        Reservation reservation = new Reservation(seat,spectator);
        reservationRepository.save(reservation);
        seat.setStatus("sold");
        seatRepository.update(seat);

    }
    public Employee loginEmployee(String username, String password){
        Employee employee = employeeRepository.findByUsername(username);
        if(employee == null)
            return null;
        if(employee.getPassword().equals(password))
            return employee;
        return null;
    }

    public Spectator loginSpectator(String username, String password){
        Spectator spectator = spectatorRepository.findByUsername(username);
        if(spectator == null)
            return null;
        if(spectator.getPassword().equals(password))
            return spectator;
        return null;
    }

    public List<Reservation> getReservation(Spectator spectator){
        List<Seat> seats = new ArrayList<>();
        List<Reservation> list = reservationRepository.getReservationsForOneSpectator(spectator);
        list.forEach(x->{Seat seat = seatRepository.findOne(x.getSeat().getId());
                        Show show = showRepository.findOne(seat.getShow().getId());
                        show.setDateToString();
                        seat.setShow(showRepository.findOne(seat.getShow().getId()));
                        x.setSeat(seat);
                        x.setSpectator(spectator); });
//        list.forEach(x->seats.add(x.getSeat()));
//        seats.forEach(x->x.setShow(showRepository.findOne(x.getShow().getId())));
//        return seats;
        return list;
    }

    public void deleteReservation(Reservation reservation){
        Seat seat = reservation.getSeat();
        seat.setStatus("free");
        seatRepository.update(seat);
        reservationRepository.delete(reservation);
    }

    public void saveShow(Show newShow) {
        showRepository.save(newShow);
    }

    public void deleteShow(Show show) {
        showRepository.delete(show);
    }

    public List<Reservation> getAllReservation() {
        List<Reservation> reservationList = new ArrayList<>();
        Iterable<Reservation> iterable = reservationRepository.findAll();
        iterable.forEach(reservationList::add);
        reservationList.forEach(x->{Seat seat = seatRepository.findOne(x.getSeat().getId());
            Show show = showRepository.findOne(seat.getShow().getId());
            show.setDateToString();
            seat.setShow(showRepository.findOne(seat.getShow().getId()));
            x.setSeat(seat);
            x.setSpectator(spectatorRepository.findOne(x.getSpectator().getId())); });
        return reservationList;
    }
}
