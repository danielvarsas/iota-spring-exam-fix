package com.vizsga.vizsga0218.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.vizsga.vizsga0218.model.Genre;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Song {

    @Id
    String id;
    String title;
    Long length;
    String lyrics;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM")
    LocalDate year;
    String writerName;
    Genre genre;
    Artist artist;
    Album album;
}