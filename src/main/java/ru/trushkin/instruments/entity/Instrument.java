package ru.trushkin.instruments.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Instrument implements Serializable {

    private final String name;
    private final Date date;
    private final BigDecimal value;

    protected Instrument(String name, Date date, BigDecimal value) {
        this.name = name;
        this.date = date;
        this.value = value;
    }

    public static Instrument of(String name, Date date, BigDecimal value) {
        return new Instrument(name, date, value);
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instrument that = (Instrument) o;
        return name.equals(that.name) &&
                date.equals(that.date) &&
                value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, value);
    }
}
