package repository.interfaces;

import domain.Reservation;
import domain.Spectator;

import java.util.List;

public interface ReservationRepository extends Repository<Integer, Reservation> {
    public List<Reservation> getReservationsForOneSpectator(Spectator spectator);
}
