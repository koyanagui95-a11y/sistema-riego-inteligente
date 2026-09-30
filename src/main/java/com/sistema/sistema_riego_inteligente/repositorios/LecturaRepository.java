package com.sistema.sistema_riego_inteligente.repositorios;

import com.sistema.sistema_riego_inteligente.Entidad.Lectura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturaRepository extends JpaRepository<Lectura, Long> {
    Lectura findTopByOrderByIdDesc();
}