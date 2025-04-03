package persistence;

import logic.Dueno;
import logic.Mascota;

import java.util.List;

public class ControladoraPersistencia {

    DuenoJpaController duenoJpaC = new DuenoJpaController();
    MascotaJpaController mascotaJpaC = new MascotaJpaController();

    public void guardar(Dueno dueno, Mascota mascota) {
        duenoJpaC.crearDueno(dueno);
        mascotaJpaC.crearMascota(mascota);
    }

    public List<Mascota> listarMascotas() {
        return mascotaJpaC.listarMascotas();
    }

    public void eliminarMascota(int numCliente) {
        mascotaJpaC.eliminarMascota(numCliente);
    }

    public void modificarMascota(Mascota mascota) {
        mascotaJpaC.actualizarMascota(mascota);
    }

    public Dueno traerDueno(int idDueno) {
        return duenoJpaC.buscarDueno(idDueno);
    }

    public void modificarDueno(Dueno dueno) {
        duenoJpaC.actualizarDueno(dueno);
    }
}
