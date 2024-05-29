package com.proyecto.ed.ProyectoED.Dao;

import com.proyecto.ed.ProyectoED.Models.Cliente;
import com.proyecto.ed.ProyectoED.Models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProductoImpl implements IProductos{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Producto> listarProductos() {
        String SQL = "SELECT * FROM Productos";
        return jdbcTemplate.query(SQL, (rs, rowNum) -> new Producto(
                rs.getLong("id"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getDouble("precio"),
                rs.getInt("stock"),
                rs.getLong("categoria_id")
        ));
    }

    @Override
    public Producto listarPorId(Long id) {
        String SQL = "SELECT * FROM Productos WHERE id = ?";
        return jdbcTemplate.queryForObject(SQL, new Object[]{id}, (rs, rowNum) -> new Producto(
                rs.getLong("id"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getDouble("precio"),
                rs.getInt("stock"),
                rs.getLong("categoria_id")
        ));
    }


    @Override
    public List<Producto> listarPorCategoria(Long categoriaId) {
        String SQL = "SELECT * FROM Productos WHERE categoria_id = ?";
        return jdbcTemplate.query(SQL, new Object[]{categoriaId}, (rs, rowNum) -> new Producto(
                rs.getLong("id"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getDouble("precio"),
                rs.getInt("stock"),
                rs.getLong("categoria_id")
        ));
    }

    @Override
    public void guardarProducto(Producto producto) {
        String SQL = "INSERT INTO Productos (nombre, descripcion, precio, stock," +
                " categoria_id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(SQL, producto.getNombre(), producto.getDescripcion(),
                producto.getPrecio(), producto.getStock(), producto.getCategoriaId());
    }

    @Override
    public void actualizarProducto(Producto producto) {
        String SQL = "UPDATE Productos SET nombre = ?, descripcion = ?, precio = ?," +
                " stock = ?, categoria_id = ? WHERE id = ?";
        jdbcTemplate.update(SQL, producto.getNombre(), producto.getDescripcion(),
                producto.getPrecio(), producto.getStock(), producto.getCategoriaId(),
                producto.getId());

    }

    @Override
    public void eliminarProducto(Long id) {
        String SQL = "DELETE FROM Productos WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }
}
