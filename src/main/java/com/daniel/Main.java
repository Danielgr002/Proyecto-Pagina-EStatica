package com.daniel;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;  // Asegúrate de importar List

public class Main {
    public static void main(String[] args) {
        // Resolver plantilla Thymeleaf
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");  // Asegúrate de que las plantillas están en la carpeta "templates"
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        // Crear contexto de Thymeleaf
        Context context = new Context();

        // Cargar el archivo JSON y deserializarlo
        Pokemons pok = cargarDatosDesdeJSON("src/main/resources/Pokemons.json");

        if (pok != null) {
            // Establecer la variable para Thymeleaf
            context.setVariable("generacions", pok.getGeneraciones());

            // Procesar la plantilla y obtener el contenido HTML
            String contingutHTML = templateEngine.process("plantilla1", context);

            // Imprimir el HTML en consola para depuración
            System.out.println(contingutHTML);

            // Escribir el HTML generado en un archivo de salida
            escriuHTML(contingutHTML, "index.html");  // Guardar el archivo generado en "output/index.html"
        } else {
            System.out.println("Error al cargar los datos desde el archivo JSON.");
        }
    }

    public static Pokemons cargarDatosDesdeJSON(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Leer el archivo JSON y convertirlo en un objeto Pokemons
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

