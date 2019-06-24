package ru.inno.stc14.entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Person extends PersonTemplate {

    @Override
    public String getName() {
        return "Smith Stewart";
    }

    @Override
    public Date getBirthDate() {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return format.parse("05.02.1988");
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
