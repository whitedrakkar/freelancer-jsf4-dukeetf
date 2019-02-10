/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.web.dukeetf;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 */
public class ETF {
    
    private double price;
    private int volume;
    private LocalDate date;
    private LocalTime time;
    private double highest52;
    private double lowest52;

    public ETF() {
    }

    public ETF(double price, int volume, LocalDate date, LocalTime time, double highest52, double lowest52) {
        this.price = price;
        this.volume = volume;
        this.date = date;
        this.time = time;
        this.highest52 = highest52;
        this.lowest52 = lowest52;
    }

    
    
    public double getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public double getHighest52() {
        return highest52;
    }

    public double getLowest52() {
        return lowest52;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setHighest52(double highest52) {
        this.highest52 = highest52;
    }

    public void setLowest52(double lowest52) {
        this.lowest52 = lowest52;
    }
}
