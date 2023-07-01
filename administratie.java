package ree;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;
import java.nio.charset.StandardCharsets;

class Administratie<T extends Student> {
    private Map<String, T> studenten;

    public Administratie() {
        studenten = new HashMap<>();
    }

    public static Administratie<Student> leesGegevensUitBestand(String bestandsnaam) {
        Administratie<Student> administratie = new Administratie<>();

        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(bestandsnaam, StandardCharsets.UTF_8));

            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                String studentnummer = (String) jsonObject.get("studentnummer");
                String naam = (String) jsonObject.get("naam");
                String klas = (String) jsonObject.get("klas");
                String studierichting = (String) jsonObject.get("studierichting");
                int studiejaar = Integer.parseInt((String) jsonObject.get("studiejaar"));

                Student student = new Student(studentnummer, naam, klas, studierichting, studiejaar);

                JSONArray behaaldeCijfersJsonArray = (JSONArray) jsonObject.get("behaalde_cijfers");
                for (Object cijferObj : behaaldeCijfersJsonArray) {
                    JSONObject cijferJsonObject = (JSONObject) cijferObj;
                    String vakcode = (String) cijferJsonObject.get("vakcode");
                    double cijfer = ((Number) cijferJsonObject.get("cijfer")).doubleValue();
                    student.voegCijferToe(new Cijfer(vakcode, cijfer));
                }

                JSONArray vakkenpakketJsonArray = (JSONArray) jsonObject.get("vakkenpakket");
                for (Object vakObj : vakkenpakketJsonArray) {
                    JSONObject vakJsonObject = (JSONObject) vakObj;
                    String vakcode = (String) vakJsonObject.get("vakcode");
                    String vakNaam = (String) vakJsonObject.get("naam");
                    int ec = ((Long) vakJsonObject.get("ec")).intValue();
                    student.voegVakToe(new Vak(vakcode, vakNaam, ec));
                }

                administratie.voegStudentToe(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return administratie;
    }

    public void voegStudentToe(T student) {
        studenten.put(student.getStudentnummer(), student);
    }

    public List<T> getStudentenInKlas(String klas) {
        List<T> studentenInKlas = new ArrayList<>();

        for (T student : studenten.values()) {
            if (student.getKlas().equals(klas)) {
                studentenInKlas.add(student);
            }
        }

        return studentenInKlas;
    }

    public List<T> getStudentenMetVak(String vakcode) {
        List<T> studentenMetVak = new ArrayList<>();

        for (T student : studenten.values()) {
            if (student.heeftVak(vakcode)) {
                studentenMetVak.add(student);
            }
        }

        return studentenMetVak;
    }

    public List<Vak> getVakkenpakketVanStudent(String studentnummer) {
        if (studenten.containsKey(studentnummer)) {
            return studenten.get(studentnummer).getVakkenpakket();
        }

        return Collections.emptyList();
    }

    public List<Vak> getNogTeHalenvanStudent(String studentnummer) {
        if (studenten.containsKey(studentnummer)) {
            return studenten.get(studentnummer).getNogTeHalen();
        }

        return Collections.emptyList();
    }

    public double getGemiddeldeVak(String vakcode) {
        List<Double> grades = studenten.values().stream()
                .flatMap(student -> student.getBehaaldeCijfers().stream())
                .filter(cijfer -> cijfer.getVakcode().equals(vakcode))
                .mapToDouble(Cijfer::getCijfer)
                .boxed()
                .collect(Collectors.toList());
    
        if (grades.isEmpty()) {
            return 0.0;
        }
    
        double total = grades.stream().mapToDouble(Double::doubleValue).sum();
        return total / grades.size();
    }

    public double getGemiddelde(String studentnummer) {
        if (studenten.containsKey(studentnummer)) {
            List<Double> grades = studenten.get(studentnummer).getBehaaldeCijfers().stream()
                    .mapToDouble(Cijfer::getCijfer)
                    .boxed()
                    .collect(Collectors.toList());
    
            if (grades.isEmpty()) {
                return 0.0;
            }
    
            double total = grades.stream().mapToDouble(Double::doubleValue).sum();
            return total / grades.size();
        }
    
        return 0.0;
    }
    

    public double getVoortgangStudie(String studentnummer) {
        if (studenten.containsKey(studentnummer)) {
            Student student = studenten.get(studentnummer);
            List<Vak> vakkenpakket = student.getVakkenpakket();
            List<Vak> behaaldeVakken = new ArrayList<>();
    
            for (Cijfer cijfer : student.getBehaaldeCijfers()) {
                for (Vak vak : vakkenpakket) {
                    if (cijfer.getVakcode().equals(vak.getVakcode())) {
                        behaaldeVakken.add(vak);
                        break;
                    }
                }
            }
    
            int totaalAantalEC = 0;
            int behaaldeEC = 0;
    
            for (Vak vak : vakkenpakket) {
                totaalAantalEC += vak.getEc();
            }
    
            for (Vak vak : behaaldeVakken) {
                behaaldeEC += vak.getEc();
            }
    
            if (totaalAantalEC == 0) {
                return 0.0;
            }
    
            return (double) behaaldeEC / totaalAantalEC * 100.0;
        }
    
        return 0.0;
    }
    

    public double getPercentageStudentenMetVak(String vakcode) {
        int studentCount = studenten.size();
        int studentenMetVakCount = 0;
    
        for (T student : studenten.values()) {
            if (student.heeftVak(vakcode)) {
                studentenMetVakCount++;
            }
        }
    
        if (studentCount == 0) {
            return 0.0;
        }
    
        return (double) studentenMetVakCount / studentCount * 100.0;
    }
    
}