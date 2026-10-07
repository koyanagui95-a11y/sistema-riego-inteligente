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

    // ==============================
    // LECTURAS
    // ==============================

    @GetMapping("/lecturas")
    public List<Lectura> obtenerTodas() {
        return lecturaServicio.obtenerTodo();
    }

    @GetMapping("/lecturas/estado")
    public ResponseEntity<Lectura> obtenerUltimoEstado() {

        Lectura lectura =
                lecturaServicio.obtenerUltimoEstado();

        if (lectura == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(lectura);
    }

    @PostMapping("/guardar")
    public ResponseEntity<Lectura> guardarLectura(
            @RequestBody Lectura lectura) {

        Lectura nuevaLectura =
                lecturaServicio.guardar(lectura);

        return new ResponseEntity<>(
                nuevaLectura,
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/lecturas/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarLectura(
            @PathVariable Long id) {

        lecturaServicio.eliminar(id);

        Map<String, Boolean> respuesta =
                new HashMap<>();

        respuesta.put("eliminado", Boolean.TRUE);

        return ResponseEntity.ok(respuesta);
    }

    // ==============================
    // CONTROL MANUAL DE LA BOMBA
    // ==============================

    @PostMapping("/bomba/manual/encender")
    public ResponseEntity<Map<String, Object>> activarManual() {

        lecturaServicio.activarManual();

        Map<String, Object> respuesta =
                new HashMap<>();

        respuesta.put("bombaManual", true);
        respuesta.put("mensaje", "Bomba activada manualmente");

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/bomba/manual/apagar")
    public ResponseEntity<Map<String, Object>> desactivarManual() {

        lecturaServicio.desactivarManual();

        Map<String, Object> respuesta =
                new HashMap<>();

        respuesta.put("bombaManual", false);
        respuesta.put("mensaje", "Bomba apagada manualmente");

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/bomba/manual")
    public ResponseEntity<Map<String, Object>> obtenerEstadoManual() {

        boolean estado =
                lecturaServicio.estaActivaManual();

        Map<String, Object> respuesta =
                new HashMap<>();

        respuesta.put("bombaManual", estado);

        return ResponseEntity.ok(respuesta);
    }
}