package ree;

public class Vak {
    private String vakcode;
    private String naam;
    private int ec;

    public Vak(String vakcode, String naam, int ec) {
        this.vakcode = vakcode;
        this.naam = naam;
        this.ec = ec;
    }

    public String getVakcode() {
        return vakcode;
    }

    public String getNaam() {
        return naam;
    }

    public int getEc() {
        return ec;
    }
}

class Cijfer {
    private String vakcode;
    private double cijfer;

    public Cijfer(String vakcode, double cijfer) {
        this.vakcode = vakcode;
        this.cijfer = cijfer;
    }

    // Getters and Setters
    public String getVakcode() {
        return vakcode;
    }

    public void setVakcode(String vakcode) {
        this.vakcode = vakcode;
    }

    public double getCijfer() {
        return cijfer;
    }

    public void setCijfer(double cijfer) {
        this.cijfer = cijfer;
    }
}