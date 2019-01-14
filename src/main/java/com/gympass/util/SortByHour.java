package com.gympass.util;

import java.util.Comparator;

import com.gympass.model.VoltaPiloto;

public class SortByHour implements Comparator<VoltaPiloto> {
    public int compare(VoltaPiloto a, VoltaPiloto b) {
        return (int) (a.getHora().getTime() - b.getHora().getTime());
    }
}