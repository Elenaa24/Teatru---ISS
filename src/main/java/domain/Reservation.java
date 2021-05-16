package domain;

public class Reservation extends Entity<Integer>{
    private Seat seat;
    private Spectator spectator;

    public Reservation( Seat seat, Spectator spectator) {
        this.seat = seat;
        this.spectator = spectator;
    }

    public Reservation() {

    }

    public Spectator getSpectator() {
        return spectator;
    }

    public void setSpectator(Spectator spectator) {
        this.spectator = spectator;
    }


    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "seat=" + seat+
                ", spectator=" + spectator +
                '}';
    }
}
