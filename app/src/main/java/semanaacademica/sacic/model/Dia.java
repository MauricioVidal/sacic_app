package semanaacademica.sacic.model;

/**
 * Created by Mauricio on 23/07/2016.
 */
public class Dia {
    private long id;
    private String diasemana;
    private int idevento;

    public int getIdevento() {
        return idevento;
    }

    public void setIdevento(int idevento) {
        this.idevento = idevento;
    }

    public Dia() {
    }

    public Dia(long id, String diasemana) {
        this.id = id;
        this.diasemana = diasemana;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDiasemana() {
        return diasemana;
    }

    public void setDiasemana(String diasemana) {
        this.diasemana = diasemana;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dia dia = (Dia) o;

        if (id != dia.id) return false;
        return diasemana.equals(dia.diasemana);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + diasemana.hashCode();
        return result;
    }
}
