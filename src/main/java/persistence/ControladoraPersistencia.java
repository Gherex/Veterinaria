package persistence;

import logic.Dueno;
import logic.Mascota;

public class ControladoraPersistencia {

    DuenoJpaController duenoJpaC = new DuenoJpaController();
    MascotaJpaController mascotaJpaC = new MascotaJpaController();

    public void guardar(Dueno dueno, Mascota mascota) {

        duenoJpaC.crearDueno(dueno);
        mascotaJpaC.crearMascota(mascota);

    }
}
