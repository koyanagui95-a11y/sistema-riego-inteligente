package com.sistema.sistema_riego_inteligente.controladores;


import com.sistema.sistema_riego_inteligente.Entidad.Lectura;
import com.sistema.sistema_riego_inteligente.servicios.ILecturaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class LecturaControlador {

    @Autowired
    private ILecturaServicio lecturaServicio;

    @GetMapping("/lecturas")
    public List<Lectura> obtenerTodas() {
        return lecturaServicio.obtenerTodo();
    }

    @GetMapping("/lecturas/estado")
    public ResponseEntity<Lectura> obtenerUltimoEstado() {
        Lectura lectura = lecturaServicio.obtenerUltimoEstado();
        if (lectura == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lectura);
    }

    @PostMapping("/guardar")
    public ResponseEntity<Lectura> guardarLectura(@RequestBody Lectura lectura) {
        Lectura nuevaLectura = lecturaServicio.guardar(lectura);
        return new ResponseEntity<>(nuevaLectura, HttpStatus.CREATED);
    }

    @DeleteMapping("/lecturas/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarLectura(@PathVariable Long id) {
        lecturaServicio.eliminar(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
