package com.gympass.util;

import java.util.Comparator;

import com.gympass.model.VoltaPiloto;

public class SortByLaptime implements Comparator<VoltaPiloto> {
    public int compare(VoltaPiloto a, VoltaPiloto b) {
        return (int) (a.getTempoVolta().getTime() - b.getTempoVolta().getTime());
    }
}