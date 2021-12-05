package com.example.fxfilmutleie2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

class Film{
    public String tittel;
    public double utLeiePris;
    public Person leidAv;

    public Film(String tittel){
        this.tittel = tittel;
        this.leidAv = null;
    }
}

class Person{
    public String navn;
    public String telefonnr;

    public Person(String navn, String telefonnr){
        this.navn = navn;
        this.telefonnr = telefonnr;
    }
}

class FilmUtleie{

    public ArrayList<Film> filmListe = new ArrayList<>();

    public FilmUtleie() {
        filmListe.add(new Film("januar"));
        filmListe.add(new Film("februar"));
        filmListe.add(new Film("mars"));
        filmListe.add(new Film("april"));
        filmListe.add(new Film("mai"));
        filmListe.add(new Film("juni"));
        filmListe.add(new Film("juli"));
        filmListe.add(new Film("august"));
        filmListe.add(new Film("september"));
        filmListe.add(new Film("oktober"));
        filmListe.add(new Film("november"));
        filmListe.add(new Film("desember"));

    }

    public String leiUt(String navn, String telefonnr, String tittel){
        String res = "";
        boolean funnet = false;
        for(int i = 0; i < filmListe.size(); i++){
            if(filmListe.get(i).tittel.equals(tittel)){
                funnet = true;
                if(filmListe.get(i).leidAv == null){
                    filmListe.get(i).leidAv = new Person(navn, telefonnr);
                    res = tittel + " er vellykket utleie.";
                }
                else {
                    res = tittel + " er utleid.";
                }
            }
        }
        if(!funnet) {
            res = tittel + " fantes ikke.";
        }
        return res;
    }

    public String leverInn(String tittel){
        String res = "";
        boolean funnet = false;
        for(int i = 0; i < filmListe.size(); i++){
            if (filmListe.get(i).tittel.equals(tittel)) {
                funnet = true;
                if(filmListe.get(i).leidAv != null) {
                    filmListe.get(i).leidAv = null;
                    res = tittel + " ble levert.";
                }
                else {
                    res = tittel + " er ikke utleid.";
                }
            }
        }
        if(!funnet){
            res = tittel + " fantes ikke.";
        }
        return res;
    }

    @Override
    public String toString(){
        String ut = "";
        String status;
        for(int i = 0; i < filmListe.size(); i++) {
            if (filmListe.get(i).leidAv == null) {
                status = " er på lager";
            } else {
                status = " er utleid";
            }
            if(filmListe.get(i).leidAv != null) {
                ut += filmListe.get(i).tittel + status + " til " + filmListe.get(i).leidAv.navn + "\n";
            }
            else {
                ut += filmListe.get(i).tittel + status + "\n";
            }
        }
        return ut;
    }
}

public class HelloController {

    FilmUtleie filmUtleie = new FilmUtleie();

    @FXML
    private TextField lblFilmtittel;

    @FXML
    private TextField lblKundenavn;

    @FXML
    private Label lblOversikt;

    @FXML
    private TextField lblTelefonnr;

    @FXML
    void btnLeiUt(ActionEvent event) {
        lblOversikt.setText(filmUtleie.leiUt(lblKundenavn.getText(), lblTelefonnr.getText(), lblFilmtittel.getText()));
    }

    @FXML
    void btnLeverinn(ActionEvent event) {
        lblOversikt.setText(filmUtleie.leverInn(lblFilmtittel.getText()));
    }

    @FXML
    void btnVisutleie(ActionEvent event) {
        lblOversikt.setText(filmUtleie.toString());
    }

}
