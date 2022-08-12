package com.java04.wibucian.models;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Entity
public class DetailIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetailIngredient", nullable = false, length = 15)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idIngredient")
    private Ingredient idIngredient;

    @Column(name = "valueChange")
    private Double valueChange;

    @Column(name = "content", nullable = false, length = 50)
    private String content;

    @Column(name = "dateTime")
    private Instant dateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Ingredient getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(Ingredient idIngredient) {
        this.idIngredient = idIngredient;
    }

    public Double getValueChange() {
        return valueChange;
    }

    public void setValueChange(Double valueChange) {
        this.valueChange = valueChange;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }

    public String formatTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime( FormatStyle.MEDIUM )
                .withLocale( Locale.UK )
                .withZone( ZoneId.systemDefault() );

        return formatter.format( this.dateTime );
    }

}