package com.proyecto.ed.ProyectoED.Repository;

import com.proyecto.ed.ProyectoED.Models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component

public interface ClienteRepository  extends JpaRepository<Cliente,Long> {

}
