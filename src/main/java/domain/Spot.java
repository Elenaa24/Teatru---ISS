package domain;

public class Spot extends Entity<Integer> {
    private int row;
    private int nr;
    private String status;
    private Show show;



    public Spot(int row, int nr, String status, Show show) {
        this.row = row;
        this.nr = nr;
        this.status = status;
        this.show = show;
    }

    public Spot() {

    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
