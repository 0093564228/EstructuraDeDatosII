package bo.edu.uagrm.ficct.inf310.ed202102.grafos.pesados.recorido_utils;

import java.util.Objects;

public class AristaConCosto implements Comparable<AristaConCosto>{
    int posicionVerticeOrigen;
    int posicionVerticeDestino;
    double costo;


    public AristaConCosto() {
    }

    public AristaConCosto(int posicionVerticeOrigen, int posicionVerticeDestino, double costo) {
        this.posicionVerticeOrigen = posicionVerticeOrigen;
        this.posicionVerticeDestino = posicionVerticeDestino;
        this.costo = costo;
    }

    public int getPosicionVerticeOrigen() {
        return posicionVerticeOrigen;
    }

    public void setPosicionVerticeOrigen(int posicionVerticeOrigen) {
        this.posicionVerticeOrigen = posicionVerticeOrigen;
    }

    public int getPosicionVerticeDestino() {
        return posicionVerticeDestino;
    }

    public void setPosicionVerticeDestino(int posicionVerticeDestino) {
        this.posicionVerticeDestino = posicionVerticeDestino;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    @Override
    public int compareTo(AristaConCosto otraAristaConCosto) {
        Double esteCosto = this.costo;
        Double elOtroCosto= otraAristaConCosto.costo;
        return esteCosto.compareTo(elOtroCosto);
        //return 0;
    }

    @Override
    public boolean equals(Object otraAristaConCosto) {
        if (this == otraAristaConCosto) {
            return true;
        }
        if (otraAristaConCosto == null || getClass() != otraAristaConCosto.getClass()) {
            return false;
        }
        AristaConCosto that = (AristaConCosto) otraAristaConCosto;
        return costo == that.costo;
//        return posicionVerticeOrigen == that.posicionVerticeOrigen
//                && posicionVerticeDestino == that.posicionVerticeDestino
//                && Double.compare(that.costo, costo) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posicionVerticeOrigen, posicionVerticeDestino, costo);
    }
}
