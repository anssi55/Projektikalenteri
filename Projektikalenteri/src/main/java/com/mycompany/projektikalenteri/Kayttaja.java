package com.mycompany.projektikalenteri;

import java.util.ArrayList;

/**
 * Created by Miika on 7.2.2018.
 */
public class Kayttaja {


    private int id;

    private String Kayttajatunnus;

    private String salasana;

    private String nayttonimi;
    
    private ArrayList<Projekti> pomona;
    
    private ArrayList<Projekti> tekijana;


    public String getKayttajatunnus() {
        return Kayttajatunnus;
    }

    public void setKayttajatunnus(String name) {
        Kayttajatunnus = name;
    }


    public String getSalasana() {
        return salasana;
    }

    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }


    public String getNayttonimi() {
        return nayttonimi;
    }

    public void setNayttonimi(String nayttonimi) {
        this.nayttonimi = nayttonimi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Projekti luoProjekti(String nimi) {
        Projekti projekti = new Projekti(nimi, this);
        pomona.add(projekti);
        return projekti;   
    }
    
            
    


    @Override
    public String toString() {
        return "Kayttaja [id=" + id + ", Kayttajatunnus=" + Kayttajatunnus + ", salasana=" + salasana + ", nayttonimi=" + nayttonimi + "]";
    }
}