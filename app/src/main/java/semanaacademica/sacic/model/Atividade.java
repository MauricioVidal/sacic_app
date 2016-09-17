package semanaacademica.sacic.model;

import java.io.Serializable;

/**
 * Created by Mauricio on 23/07/2016.
 */
public class Atividade implements Serializable {
    private int id;
    private String nome;
    private String horario;
    private String duracao;
    private String info;
    private int iddia;
    private int idtipo;
    private int participar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getIddia() {
        return iddia;
    }

    public void setIddia(int iddia) {
        this.iddia = iddia;
    }

    public int getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(int idtipo) {
        this.idtipo = idtipo;
    }

    public int getParticipar() {
        return participar;
    }

    public void setParticipar(int participar) {
        this.participar = participar;
    }
}
