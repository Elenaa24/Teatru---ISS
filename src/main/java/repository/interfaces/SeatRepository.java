package repository.interfaces;

import domain.Seat;
import domain.Show;

import java.util.List;

public interface SeatRepository extends Repository<Integer, Seat> {
    List<Seat> getFreeSeats(Show show);
}
