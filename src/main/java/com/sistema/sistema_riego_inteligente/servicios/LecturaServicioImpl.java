package com.sistema.sistema_riego_inteligente.servicios;

import com.sistema.sistema_riego_inteligente.Entidad.Lectura;
import com.sistema.sistema_riego_inteligente.repositorios.LecturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.time.ZoneId;

@Service
public class LecturaServicioImpl implements ILecturaServicio {

    @Autowired
    private LecturaRepository lecturaRepositorio;

    @Override
    public Lectura guardar(Lectura nuevaLectura) {
        Lectura ultimaLectura = lecturaRepositorio.findTopByOrderByIdDesc();

        if (ultimaLectura == null) {
            return lecturaRepositorio.save(nuevaLectura);
        }

        boolean bombaNueva = Boolean.TRUE.equals(nuevaLectura.getBombaActiva());
        boolean bombaUltima = Boolean.TRUE.equals(ultimaLectura.getBombaActiva());
        boolean cambioBomba = bombaNueva != bombaUltima;

        boolean tiempoExcedido = false;
        if (ultimaLectura.getFechaRegistro() != null) {
            long segundos = Duration.between(ultimaLectura.getFechaRegistro(), LocalDateTime.now(ZoneId.of("America/Lima"))).getSeconds();
            tiempoExcedido = segundos >= 300;
        }

        if (cambioBomba || tiempoExcedido) {
            return lecturaRepositorio.save(nuevaLectura);
        }

        return nuevaLectura;
    }

    @Override
    public List<Lectura> obtenerTodo() {
        return lecturaRepositorio.findAll();
    }

    @Override
    public Lectura obtenerPorId(Long id) {
        return lecturaRepositorio.findById(id).orElse(null);
    }

    @Override
    public Lectura obtenerUltimoEstado() {
        return lecturaRepositorio.findTopByOrderByIdDesc();
    }

    @Override
    public void eliminar(Long id) {
        lecturaRepositorio.deleteById(id);
    }
}