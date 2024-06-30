package com.proyecto.ed.ProyectoED.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ComprasImpl implements ICompras{
    @Autowired
    private JdbcTemplate jdbcTemplate;






}
