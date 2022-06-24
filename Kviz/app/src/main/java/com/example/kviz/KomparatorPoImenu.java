package com.example.kviz;

import java.util.Comparator;

import db.Rec;

public class KomparatorPoImenu implements Comparator<Rec> {

    @Override
    public int compare(Rec rec, Rec t1) {
        return rec.nemacki.compareTo(t1.nemacki);
    }
}
