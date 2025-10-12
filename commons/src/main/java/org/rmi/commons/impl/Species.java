package org.rmi.commons.impl;
import org.rmi.commons.interfaces.ISpecies;
import java.io.Serializable;

public class Species implements Serializable, ISpecies {
    private String nom;
    private int lifespan;


    public Species(String nom, int lifespan){
        this.nom = nom;
        this.lifespan = lifespan;
    }
    @Override
    public String getName() {
        return this.nom;
    }

    @Override
    public int getLifeSpan() {
        return this.lifespan;
    }
}
