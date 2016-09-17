package semanaacademica.sacic.model;

/**
 * Created by Mauricio on 23/07/2016.
 */
public class Evento {
    private int id;
    private String titulo;
    private String descricao;
    private String datasinc;

    public Evento() {
    }

    public Evento(int id, String titulo){
        this.id = id;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDatasinc() {
        return datasinc;
    }

    public void setDatasinc(String datasinc) {
        this.datasinc = datasinc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Evento evento = (Evento) o;

        if (id != evento.id) return false;
        return titulo.equals(evento.titulo);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + titulo.hashCode();
        return result;
    }
}
