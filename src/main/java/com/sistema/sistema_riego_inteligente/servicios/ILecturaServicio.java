package com.sistema.sistema_riego_inteligente.servicios;

import com.sistema.sistema_riego_inteligente.Entidad.Lectura;
import java.util.List;

public interface ILecturaServicio {

    public List<Lectura> obtenerTodo();

    public Lectura guardar(Lectura lectura);

    public Lectura obtenerPorId(Long id);

    public Lectura obtenerUltimoEstado();

    public void eliminar(Long id);

    public void activarManual();

    public void desactivarManual();

    public boolean estaActivaManual();
}