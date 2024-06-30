package com.proyecto.ed.ProyectoED.Dao;

import com.proyecto.ed.ProyectoED.Models.Administrador;
import com.proyecto.ed.ProyectoED.Models.CategoriaProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CategoriaProductoImpl implements ICategoriaProductos{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<CategoriaProducto> listarCategorias() {
        String sql = "SELECT * FROM Categoria_Productos";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new CategoriaProducto(
                rs.getLong("id"),
                rs.getString("nombre"),
                rs.getString("descripcion")
        ));
    }

    @Override
    public CategoriaProducto listarPorId(Long id) {
        String sql = "Select * from Categoria_Productos where id = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id}, (rs, rowNum) -> new CategoriaProducto(
                rs.getLong("id"),
                rs.getString("nombre"),
                rs.getString("descripcion")

        ));
    }


    @Override
    public void guardarCategoria(CategoriaProducto categoriaProducto) {
        String sql = "INSERT INTO Categoria_Productos (nombre, descripcion) VALUES (?, ?)";
        jdbcTemplate.update(sql, categoriaProducto.getNombre(),
                categoriaProducto.getDescripcion());

    }

    @Override
    public void actualizarCategoria(CategoriaProducto categoriaProducto) {
        String sql = "UPDATE Categoria_Productos SET nombre = ?, " +
                "descripcion = ? WHERE id = ?";
        jdbcTemplate.update(sql, categoriaProducto.getNombre(),
                categoriaProducto.getDescripcion(), categoriaProducto.getId());

    }

    @Override
    public void eliminarCategoria(Long id) {
        String sql = "DELETE FROM Categoria_Productos WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
