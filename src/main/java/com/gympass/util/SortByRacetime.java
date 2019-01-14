package com.gympass.util;

import java.util.Comparator;

import com.gympass.model.ProvaPiloto;

public class SortByRacetime implements Comparator<ProvaPiloto> {

    @Override
    public int compare(ProvaPiloto s1, ProvaPiloto s2) {
        return s1.getTempoTotalProva().compareTo(s2.getTempoTotalProva());
    }
}