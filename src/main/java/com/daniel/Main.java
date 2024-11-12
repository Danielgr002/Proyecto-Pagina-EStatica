package com.daniel;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();

        Properties properties = new Properties();
        String path = "src/main/resources/config.ini";
        try {
            FileInputStream input = new FileInputStream(path);
            InputStreamReader reader = new InputStreamReader(input);
            properties.load(reader);
        } catch (IOException e){
            e.printStackTrace();
        }



        String nom = properties.getProperty("nom");
        String descripcio = properties.getProperty("descripcio");

        //System.out.println(nom);
        //System.out.println(descripcio);
        Pokemons pok = cargarDatosDesdeJSON("src/main/resources/Pokemons.json");

        if (pok != null) {
            context.setVariable("nom", nom);
            context.setVariable("descripcio", descripcio);
            context.setVariable("generacions", pok.getGeneraciones());

            String contingutHTML = templateEngine.process("plantilla1", context);

            //System.out.println(contingutHTML);

            escriuHTML(contingutHTML, "src/main/resources/static/index.html");

            for (Generaciones generacion : pok.getGeneraciones()) {
                Context contextDetalles = new Context();
                contextDetalles.setVariable("generacion", generacion);

                String detallesHTML = templateEngine.process("plantilla2", contextDetalles);
                String fileName = "src/main/resources/static/detalles_" + generacion.getId() + ".html";

                escriuHTML(detallesHTML, fileName);
            }
        } else {
            System.out.println("Error al cargar los datos desde el archivo JSON.");
        }

    }

    public static Pokemons cargarDatosDesdeJSON(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(path), Pokemons.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void escriuHTML(String contingut, String nom) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nom))) {
            writer.write(contingut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

