package domain;

public class Reservation extends Entity<Integer>{
    private int nr_tickets;
    private Spot spot;
    private Spectator spectator;

    public Reservation(int nr_tickets, Spot spot, Spectator spectator) {
        this.nr_tickets = nr_tickets;
        this.spot = spot;
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

    public int getNr_tickets() {
        return nr_tickets;
    }

    public void setNr_tickets(int nr_tickets) {
        this.nr_tickets = nr_tickets;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }


}
