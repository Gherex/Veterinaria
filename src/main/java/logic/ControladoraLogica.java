package logic;

import persistence.ControladoraPersistencia;

public class ControladoraLogica {

    ControladoraPersistencia controlPersistence = new ControladoraPersistencia();

    public void guardar(String nombre, String raza, String color, String observaciones,
                        String alergico, String atenEspecial, String nombreDueno, String celDueno) {

        Dueno dueno = new Dueno();
        dueno.setNombre(nombreDueno);
        dueno.setCelDueno(celDueno);

        Mascota mascota = new Mascota();
        mascota.setNombre(nombre);
        mascota.setRaza(raza);
        mascota.setColor(color);
        mascota.setObservaciones(observaciones);
        mascota.setAlergico(alergico);
        mascota.setAtencion_especial(atenEspecial);
        mascota.setUnDueno(dueno);

        controlPersistence.guardar(dueno, mascota);

    }
}
