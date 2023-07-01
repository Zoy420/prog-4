package ree;

import java.util.*;

class Student {
    private String studentnummer;
    private String naam;
    private String klas;
    private String studierichting;
    private int studiejaar;
    private List<Vak> vakkenpakket;
    private List<Cijfer> behaaldeCijfers;

    public Student(String studentnummer, String naam, String klas, String studierichting, int studiejaar) {
        this.studentnummer = studentnummer;
        this.naam = naam;
        this.klas = klas;
        this.studierichting = studierichting;
        this.studiejaar = studiejaar;
        this.vakkenpakket = new ArrayList<>();
        this.behaaldeCijfers = new ArrayList<>();
    }

    // Getters and Setters
    public String getStudentnummer() {
        return studentnummer;
    }

    public void setStudentnummer(String studentnummer) {
        this.studentnummer = studentnummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getKlas() {
        return klas;
    }

    public void setKlas(String klas) {
        this.klas = klas;
    }

    public String getStudierichting() {
        return studierichting;
    }

    public void setStudierichting(String studierichting) {
        this.studierichting = studierichting;
    }

    public int getStudiejaar() {
        return studiejaar;
    }

    public void setStudiejaar(int studiejaar) {
        this.studiejaar = studiejaar;
    }

    public List<Vak> getVakkenpakket() {
        return vakkenpakket;
    }

    public void setVakkenpakket(List<Vak> vakkenpakket) {
        this.vakkenpakket = vakkenpakket;
    }

    public List<Cijfer> getBehaaldeCijfers() {
        return behaaldeCijfers;
    }

    public void setBehaaldeCijfers(List<Cijfer> behaaldeCijfers) {
        this.behaaldeCijfers = behaaldeCijfers;
    }

    // Other methods
    public void voegVakToe(Vak vak) {
        vakkenpakket.add(vak);
    }

    public void voegCijferToe(Cijfer cijfer) {
        behaaldeCijfers.add(cijfer);
    }

    public boolean heeftVak(String vakcode) {
        for (Vak vak : vakkenpakket) {
            if (vak.getVakcode().equals(vakcode)) {
                return true;
            }
        }
        return false;
    }

    public List<Vak> getNogTeHalen() {
        List<Vak> nogTeHalenVakken = new ArrayList<>();
        for (Vak vak : vakkenpakket) {
            boolean vakBehaald = false;
            for (Cijfer cijfer : behaaldeCijfers) {
                if (cijfer.getVakcode().equals(vak.getVakcode())) {
                    vakBehaald = true;
                    break;
                }
            }
            if (!vakBehaald) {
                nogTeHalenVakken.add(vak);
            }
        }
        return nogTeHalenVakken;
    }

    public double berekenGemiddelde() {
        if (behaaldeCijfers.isEmpty()) {
            return 0.0;
        }

        double totaal = 0.0;
        for (Cijfer cijfer : behaaldeCijfers) {
            totaal += cijfer.getCijfer();
        }
        return totaal / behaaldeCijfers.size();
    }
}