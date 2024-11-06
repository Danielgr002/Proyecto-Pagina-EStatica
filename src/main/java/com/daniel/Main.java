package com.daniel;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");  // Asegúrate de que las plantillas están en la carpeta "templates"
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();

        Pokemons pok = cargarDatosDesdeJSON("src/main/resources/Pokemons.json");

        if (pok != null) {
            context.setVariable("generacions", pok.getGeneraciones());

            String contingutHTML = templateEngine.process("plantilla1", context);

            System.out.println(contingutHTML);

            escriuHTML(contingutHTML, "index.html");
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

