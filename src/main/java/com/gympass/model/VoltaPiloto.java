package com.gympass.model;

import java.io.Serializable;
import java.util.Date;

public class VoltaPiloto implements Serializable {

    private static final long serialVersionUID = 4023886643151257679L;
    
    private Date hora;
    
    private Integer numeroPiloto;
    
    private String nomePiloto;
    
    private Integer numeroVolta;
    
    private Date tempoVolta;
    
    private Double velocidadeMediaVolta;

    public VoltaPiloto(Date hora, Integer numeroPiloto, String nomePiloto, Integer numeroVolta, Date tempoVolta,
            Double velocidadeMediaVolta) {
        super();
        this.hora = hora;
        this.numeroPiloto = numeroPiloto;
        this.nomePiloto = nomePiloto;
        this.numeroVolta = numeroVolta;
        this.tempoVolta = tempoVolta;
        this.velocidadeMediaVolta = velocidadeMediaVolta;
    }

    public VoltaPiloto() {
        super();
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Integer getNumeroPiloto() {
        return numeroPiloto;
    }

    public void setNumeroPiloto(Integer numeroPiloto) {
        this.numeroPiloto = numeroPiloto;
    }

    public String getNomePiloto() {
        return nomePiloto;
    }

    public void setNomePiloto(String nomePiloto) {
        this.nomePiloto = nomePiloto;
    }

    public Integer getNumeroVolta() {
        return numeroVolta;
    }

    public void setNumeroVolta(Integer numeroVolta) {
        this.numeroVolta = numeroVolta;
    }

    public Date getTempoVolta() {
        return tempoVolta;
    }

    public void setTempoVolta(Date tempoVolta) {
        this.tempoVolta = tempoVolta;
    }

    public Double getVelocidadeMediaVolta() {
        return velocidadeMediaVolta;
    }

    public void setVelocidadeMediaVolta(Double velocidadeMediaVolta) {
        this.velocidadeMediaVolta = velocidadeMediaVolta;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hora == null) ? 0 : hora.hashCode());
        result = prime * result + ((nomePiloto == null) ? 0 : nomePiloto.hashCode());
        result = prime * result + ((numeroPiloto == null) ? 0 : numeroPiloto.hashCode());
        result = prime * result + ((numeroVolta == null) ? 0 : numeroVolta.hashCode());
        result = prime * result + ((tempoVolta == null) ? 0 : tempoVolta.hashCode());
        result = prime * result + ((velocidadeMediaVolta == null) ? 0 : velocidadeMediaVolta.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VoltaPiloto other = (VoltaPiloto) obj;
        if (hora == null) {
            if (other.hora != null)
                return false;
        } else if (!hora.equals(other.hora))
            return false;
        if (nomePiloto == null) {
            if (other.nomePiloto != null)
                return false;
        } else if (!nomePiloto.equals(other.nomePiloto))
            return false;
        if (numeroPiloto == null) {
            if (other.numeroPiloto != null)
                return false;
        } else if (!numeroPiloto.equals(other.numeroPiloto))
            return false;
        if (numeroVolta == null) {
            if (other.numeroVolta != null)
                return false;
        } else if (!numeroVolta.equals(other.numeroVolta))
            return false;
        if (tempoVolta == null) {
            if (other.tempoVolta != null)
                return false;
        } else if (!tempoVolta.equals(other.tempoVolta))
            return false;
        if (velocidadeMediaVolta == null) {
            if (other.velocidadeMediaVolta != null)
                return false;
        } else if (!velocidadeMediaVolta.equals(other.velocidadeMediaVolta))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "VoltaPiloto [hora=" + hora + ", numeroPiloto=" + numeroPiloto + ", nomePiloto=" + nomePiloto
                + ", numeroVolta=" + numeroVolta + ", tempoVolta=" + tempoVolta + ", velocidadeMediaVolta="
                + velocidadeMediaVolta + "]";
    }

}