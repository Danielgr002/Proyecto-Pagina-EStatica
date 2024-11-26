package com.daniel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        //Hola, esto es una excusa para hacer un commit
        try {
            JsonNode json = JsonLoader.fromFile(new File("src/main/resources/Pokemons.json"));
            JsonNode schema = JsonLoader.fromFile(new File("src/main/resources/Pokemons-schema.json"));

            JsonSchemaFactory jsonSchemaFcatory = JsonSchemaFactory.byDefault();
            JsonSchema jsonSchema = jsonSchemaFcatory.getJsonSchema(schema);
            ProcessingReport report = jsonSchema.validate(json);

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        String config = "src/main/resources/config.ini";
        Properties properties = new Properties();
        try {
            InputStreamReader input = new InputStreamReader(new FileInputStream(config));
            properties.load(input);
        } catch (IOException e){
            e.printStackTrace();
        }

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        String nom = properties.getProperty("nom");
        String descripcio = properties.getProperty("descripcio");

        if (report.isSuccess()) {
            Context context = new Context();

            Pokemons pok = cargarDatosDesdeJSON("src/main/resources/Pokemons.json");

            if (pok != null) {
                context.setVariable("generacions", pok.getGeneraciones());
                context.setVariable("nom", nom);
                context.setVariable("descripcio", descripcio);
                String contingutHTML = templateEngine.process("plantilla1", context);

                escriuHTML(contingutHTML, "src/main/resources/static/index.html");

                for (Generaciones generacion : pok.getGeneraciones()) {
                    Context contextDetalles = new Context();
                    contextDetalles.setVariable("generacion", generacion);
                    String detallesHTML = templateEngine.process("plantilla2", contextDetalles);
                    String fileName = "src/main/resources/static/detalles_" + generacion.getId() + ".html";

                    escriuHTML(detallesHTML, fileName);
                }
                String rutaRSS = "src/main/resources/static/rss.xml";
                generaRSS(pok, rutaRSS, nom, descripcio);
            } else {
                System.out.println("Error al cargar los datos desde el archivo JSON.");
            }
        }
        }catch (IOException | ProcessingException e){
            System.out.println("Error al validar el json schema con el json.");
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
    public static void generaRSS(Pokemons pok, String rutaRSS, String nom, String descripcio){
        try {
            FileWriter fw = new FileWriter(rutaRSS);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            bw.write("<rss version=\"2.0\">\n");
            bw.write("<channel>\n");
            bw.write("<title>"+nom+"</title>\n");
            bw.write("<link>src/main/resources/static/index.html</link>\n");
            bw.write("<description>"+descripcio+"</description>\n");
            for (Generaciones generacion: pok.getGeneraciones()){
                bw.write("<item>\n");
                bw.write("<title>" + generacion.getNom() + "</title>\n");
                bw.write("<link>src/main/resources/static/detalles_" + generacion.getId() + ".html</link>\n");
                bw.write("<description>" + generacion.getLocalitzacio() + "</description>\n");
                bw.write("</item>\n");
            }
            bw.write("</channel>\n");
            bw.write("</rss>\n");
            bw.close();
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}