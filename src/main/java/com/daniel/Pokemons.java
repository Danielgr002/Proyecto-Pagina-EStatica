package com.daniel;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Pokemons {
    @JsonProperty("generacions")
    private List<Generaciones> generaciones;

    public Pokemons() {
    }

    public Pokemons(List<Generaciones> generaciones) {
        this.generaciones = generaciones;
    }

    public List<Generaciones> getGeneraciones() {
        return generaciones;
    }

    public void setGeneraciones(List<Generaciones> generaciones) {
        this.generaciones = generaciones;
    }
}

class Generaciones{
    @JsonProperty("id")
    private int id;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("any_lançament")
    private int any_lançament;
    @JsonProperty("jocs")
    private ArrayList<String> jocs;
    @JsonProperty("gimnassos")
    private ArrayList<Gimnasios> gimnassos;

    public Generaciones(int id, String nom, int any_lançament, ArrayList<String> jocs, ArrayList<Gimnasios> gimnassos) {
        this.id = id;
        this.nom = nom;
        this.any_lançament = any_lançament;
        this.jocs = jocs;
        this.gimnassos = gimnassos;
    }

    public Generaciones() {
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getAny_lançament() {
        return any_lançament;
    }

    public ArrayList<String> getJocs() {
        return jocs;
    }

    public ArrayList<Gimnasios> getGimnassos() {
        return gimnassos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAny_lançament(int any_lançament) {
        this.any_lançament = any_lançament;
    }

    public void setJocs(ArrayList<String> jocs) {
        this.jocs = jocs;
    }

    public void setGimnassos(ArrayList<Gimnasios> gimnassos) {
        this.gimnassos = gimnassos;
    }
}

class Gimnasios {
    @JsonProperty("id")
    private int id;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("lider")
    private String lider;
    @JsonProperty("tipus")
    private List<String> tipos = new ArrayList<>();
    @JsonProperty("localitzacio")
    private String localizacion;

    public Gimnasios(){
    }

    public Gimnasios(int id, String nom, String lider, ArrayList<String> tipus, String localizacion) {
        this.id = id;
        this.nom = nom;
        this.lider = lider;
        this.tipos = tipus;
        this.localizacion = localizacion;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getLider() {
        return lider;
    }

    public List<String> getTipus() {
        return tipos;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLider(String lider) {
        this.lider = lider;
    }

    public void setTipus(ArrayList<String> tipus) {
        this.tipos = tipus;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }
}
