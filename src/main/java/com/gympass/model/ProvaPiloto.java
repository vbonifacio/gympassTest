package com.gympass.model;

import java.io.Serializable;

public class ProvaPiloto implements Serializable {

    private static final long serialVersionUID = -7710035139172765764L;
    
    private Integer numeroPiloto;
    
    private Long tempoTotalProva;
    
    private Integer numeroVoltasCompletadas;

    public ProvaPiloto(Integer numeroPiloto, Long tempoTotalProva,
            Integer numeroVoltasCompletadas) {
        super();
        this.numeroPiloto = numeroPiloto;
        this.tempoTotalProva = tempoTotalProva;
        this.numeroVoltasCompletadas = numeroVoltasCompletadas;
    }

    public ProvaPiloto() {
        super();
    }

    public Integer getNumeroPiloto() {
        return numeroPiloto;
    }

    public void setNumeroPiloto(Integer numeroPiloto) {
        this.numeroPiloto = numeroPiloto;
    }

    public Long getTempoTotalProva() {
        return tempoTotalProva;
    }

    public void setTempoTotalProva(Long tempoTotalProva) {
        this.tempoTotalProva = tempoTotalProva;
    }

    public Integer getNumeroVoltasCompletadas() {
        return numeroVoltasCompletadas;
    }

    public void setNumeroVoltasCompletadas(Integer numeroVoltasCompletadas) {
        this.numeroVoltasCompletadas = numeroVoltasCompletadas;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((numeroPiloto == null) ? 0 : numeroPiloto.hashCode());
        result = prime * result + ((numeroVoltasCompletadas == null) ? 0
                : numeroVoltasCompletadas.hashCode());
        result = prime * result
                + ((tempoTotalProva == null) ? 0 : tempoTotalProva.hashCode());
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
        ProvaPiloto other = (ProvaPiloto) obj;
        if (numeroPiloto == null) {
            if (other.numeroPiloto != null)
                return false;
        } else if (!numeroPiloto.equals(other.numeroPiloto))
            return false;
        if (numeroVoltasCompletadas == null) {
            if (other.numeroVoltasCompletadas != null)
                return false;
        } else if (!numeroVoltasCompletadas
                .equals(other.numeroVoltasCompletadas))
            return false;
        if (tempoTotalProva == null) {
            if (other.tempoTotalProva != null)
                return false;
        } else if (!tempoTotalProva.equals(other.tempoTotalProva))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ProvaPiloto [numeroPiloto=" + numeroPiloto
                + ", tempoTotalProva=" + tempoTotalProva
                + ", numeroVoltasCompletadas=" + numeroVoltasCompletadas + "]";
    }

}
