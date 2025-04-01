package logic;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Dueno {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_dueno;
    private String nombre;
    private String celDueno;

    public Dueno() {
    }

    public Dueno(String celDueno, int id_dueno, String nombre) {
        this.celDueno = celDueno;
        this.id_dueno = id_dueno;
        this.nombre = nombre;
    }

    public String getCelDueno() {
        return celDueno;
    }

    public void setCelDueno(String celDueno) {
        this.celDueno = celDueno;
    }

    public int getId_dueno() {
        return id_dueno;
    }

    public void setId_dueno(int id_dueno) {
        this.id_dueno = id_dueno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
