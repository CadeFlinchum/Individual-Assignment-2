package com.csc340.assignment2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Assignment2Application {

    public static void main(String[] args) {
        SpringApplication.run(Assignment2Application.class, args);
        getAnimeQuote();
        System.exit(0);
    }

    public static void getAnimeQuote() {
        try {
            String url = "https://animechan.xyz/api/random";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonFact = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonFact);

            String anime = root.findValue("anime").asText();
            String character = root.findValue("character").asText();
            String quote = root.findValue("quote").asText();

            System.out.println("\n**********Anime Quote********");
            System.out.println("Anime: " + anime);
            System.out.println("Character: " + character);
            System.out.println("Quote: " + quote + "\n");

        } catch (JsonProcessingException ex) {
            Logger.getLogger(Assignment2Application.class.getName()).log(
                    Level.SEVERE,
                    null, ex);
            System.out.println("error in getAnimeQuote");

        }
    }

}
