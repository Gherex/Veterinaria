package logic;

public class Mascota {

    private int num_cliente;
    private String nombre;
    private String raza;
    private String color;
    private String alergico;
    private String atencion_especial;
    private String observaciones;
    private Dueno unDueno;

    public Mascota() {
    }

    public Mascota(String alergico, String atencion_especial, String color, String nombre, int num_cliente, String observaciones, String raza, Dueno unDueno) {
        this.alergico = alergico;
        this.atencion_especial = atencion_especial;
        this.color = color;
        this.nombre = nombre;
        this.num_cliente = num_cliente;
        this.observaciones = observaciones;
        this.raza = raza;
        this.unDueno = unDueno;
    }

    public String getAlergico() {
        return alergico;
    }

    public void setAlergico(String alergico) {
        this.alergico = alergico;
    }

    public String getAtencion_especial() {
        return atencion_especial;
    }

    public void setAtencion_especial(String atencion_especial) {
        this.atencion_especial = atencion_especial;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNum_cliente() {
        return num_cliente;
    }

    public void setNum_cliente(int num_cliente) {
        this.num_cliente = num_cliente;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Dueno getUnDueno() {
        return unDueno;
    }

    public void setUnDueno(Dueno unDueno) {
        this.unDueno = unDueno;
    }
}
