package com.daniel;

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
    @JsonProperty("any_lanzamiento")
    private int any_lanzamiento;
    @JsonProperty("jocs")
    private List<String> jocs;
    @JsonProperty("localitzacio")
    private String localitzacio;
    @JsonProperty("gimnassos")
    private List<Gimnasios> gimnassos;

    public Generaciones() {
    }

    public Generaciones(int id, String nom, int any_lanzamiento, List<String> jocs, String localitzacio, List<Gimnasios> gimnassos) {
        this.id = id;
        this.nom = nom;
        this.any_lanzamiento = any_lanzamiento;
        this.jocs = jocs;
        this.localitzacio = localitzacio;
        this.gimnassos = gimnassos;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getAny_lanzamiento() {
        return any_lanzamiento;
    }

    public List<String> getJocs() {
        return jocs;
    }

    public List<Gimnasios> getGimnassos() {
        return gimnassos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAny_lanzamiento(int any_lanzamiento) {
        this.any_lanzamiento = any_lanzamiento;
    }

    public String getLocalitzacio() {
        return localitzacio;
    }

    public void setLocalitzacio(String localitzacio) {
        this.localitzacio = localitzacio;
    }

    public void setJocs(List<String> jocs) {
        this.jocs = jocs;
    }

    public void setGimnassos(List<Gimnasios> gimnassos) {
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
    @JsonProperty("fotoUrl")
    private String fotoUrl;
    @JsonProperty("tipus")
    private List<String> tipus;
    @JsonProperty("pokemons")
    private List<String> pokemons;

    public Gimnasios(){
    }

    public Gimnasios(int id, String nom, String fotoUrl, String lider, List<String> tipus, List<String> pokemons) {
        this.id = id;
        this.nom = nom;
        this.fotoUrl = fotoUrl;
        this.lider = lider;
        this.tipus = tipus;
        this.pokemons = pokemons;
    }

    public List<String> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<String> pokemons) {
        this.pokemons = pokemons;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
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
        return tipus;
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

    public void setTipus(List<String> tipus) {
        this.tipus = tipus;
    }
}
