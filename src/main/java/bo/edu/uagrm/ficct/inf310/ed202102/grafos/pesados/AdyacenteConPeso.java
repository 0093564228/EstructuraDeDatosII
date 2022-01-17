package bo.edu.uagrm.ficct.inf310.ed202102.grafos.pesados;

import java.util.Objects;

public class AdyacenteConPeso implements Comparable<AdyacenteConPeso> {
    private int indiceDeVertice;
    private double peso;

    public AdyacenteConPeso(int indiceDeVertice) {
        this.indiceDeVertice = indiceDeVertice;
    }

    public AdyacenteConPeso(int indiceDeVertice, double peso) {
        this.indiceDeVertice = indiceDeVertice;
        this.peso = peso;
    }

    public int getIndiceDeVertice() {
        return indiceDeVertice;
    }

    public void setIndiceDeVertice(int indiceDeVertice) {
        this.indiceDeVertice = indiceDeVertice;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public int compareTo(AdyacenteConPeso elOtroAdyacenteConPeso) {
        Integer esteVertice = this.indiceDeVertice;
        Integer elOtroVertice = elOtroAdyacenteConPeso.indiceDeVertice;
        return esteVertice.compareTo(elOtroVertice);
    }

    @Override
    public boolean equals(Object elOtroAdyacente) {
        if (this == elOtroAdyacente) {
            return true;
        }
        if (elOtroAdyacente == null || getClass() != elOtroAdyacente.getClass()) {
            return false;
        }
        AdyacenteConPeso that = (AdyacenteConPeso) elOtroAdyacente;
        return indiceDeVertice == that.indiceDeVertice;// && Double.compare(that.peso, peso) == 0
    }

    @Override
    public int hashCode() {
        return Objects.hash(indiceDeVertice, peso);
    }
}
