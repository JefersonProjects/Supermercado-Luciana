package com.proyecto.ed.ProyectoED.Dao;


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
        String SQL = "SELECT p.*, c.nombre AS categoria_nombre FROM Productos p JOIN Categoria_Productos c ON p.categoria_id = c.id";
        return jdbcTemplate.query(SQL, (rs, rowNum) -> new Producto(
                rs.getLong("id"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getDouble("precio"),
                rs.getInt("stock"),
                rs.getLong("categoria_id"),
                rs.getString("categoria_nombre"),
                rs.getBytes("imagen")
        ));
    }

    @Override
    public Producto listarPorId(Long id) {
        String SQL = "SELECT p.*, c.nombre AS categoria_nombre FROM Productos p JOIN Categoria_Productos c ON p.categoria_id = c.id WHERE p.id = ?";
        return jdbcTemplate.queryForObject(SQL, new Object[]{id}, (rs, rowNum) -> new Producto(
                rs.getLong("id"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getDouble("precio"),
                rs.getInt("stock"),
                rs.getLong("categoria_id"),
                rs.getString("categoria_nombre"),
                rs.getBytes("imagen")
        ));
    }

    @Override
    public List<Producto> listarPorCategoria(String categoriaNombre) {
        String SQL = "SELECT p.*, c.nombre AS categoria_nombre FROM Productos p JOIN Categoria_Productos c ON p.categoria_id = c.id WHERE LOWER(c.nombre) = LOWER(?)";
        return jdbcTemplate.query(SQL, new Object[]{categoriaNombre}, (rs, rowNum) -> new Producto(
                rs.getLong("id"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getDouble("precio"),
                rs.getInt("stock"),
                rs.getLong("categoria_id"),
                rs.getString("categoria_nombre"),
                rs.getBytes("imagen")
        ));
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        String SQL = "SELECT p.*, c.nombre AS categoria_nombre FROM Productos p JOIN Categoria_Productos c ON p.categoria_id = c.id WHERE LOWER(p.nombre) LIKE LOWER(?)";
        return jdbcTemplate.query(SQL, new Object[]{"%" + nombre + "%"}, (rs, rowNum) -> new Producto(
                rs.getLong("id"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getDouble("precio"),
                rs.getInt("stock"),
                rs.getLong("categoria_id"),
                rs.getString("categoria_nombre"),
                rs.getBytes("imagen")
        ));
    }

    @Override
    public List<Producto> filtrarPorPrecio(Double min, Double max) {
        String SQL = "SELECT p.*, c.nombre AS categoria_nombre FROM Productos p JOIN Categoria_Productos c ON p.categoria_id = c.id WHERE precio BETWEEN ? AND ?";
        return jdbcTemplate.query(SQL, new Object[]{min, max}, (rs, rowNum) -> new Producto(
                rs.getLong("id"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getDouble("precio"),
                rs.getInt("stock"),
                rs.getLong("categoria_id"),
                rs.getString("categoria_nombre"),
                rs.getBytes("imagen")
        ));
    }

    @Override
    public List<Producto> ordenarProductos(String criterio) {
        String SQL;
        switch (criterio) {
            case "nombre":
                SQL = "SELECT p.*, c.nombre AS categoria_nombre FROM Productos p JOIN Categoria_Productos c ON p.categoria_id = c.id ORDER BY p.nombre";
                break;
            case "precio_asc":
                SQL = "SELECT p.*, c.nombre AS categoria_nombre FROM Productos p JOIN Categoria_Productos c ON p.categoria_id = c.id ORDER BY p.precio ASC";
                break;
            case "precio_desc":
                SQL = "SELECT p.*, c.nombre AS categoria_nombre FROM Productos p JOIN Categoria_Productos c ON p.categoria_id = c.id ORDER BY p.precio DESC";
                break;
            default:
                SQL = "SELECT p.*, c.nombre AS categoria_nombre FROM Productos p JOIN Categoria_Productos c ON p.categoria_id = c.id";

        }
        return jdbcTemplate.query(SQL, (rs, rowNum) -> new Producto(
                rs.getLong("id"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getDouble("precio"),
                rs.getInt("stock"),
                rs.getLong("categoria_id"),
                rs.getString("categoria_nombre"),
                rs.getBytes("imagen")
        ));
    }


    @Override
    public void guardarProducto(Producto producto) {
        String SQL = "INSERT INTO Productos (nombre, descripcion, precio, stock, categoria_id, imagen) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(SQL, producto.getNombre(), producto.getDescripcion(), producto.getPrecio(),
                producto.getStock(), producto.getCategoriaId(), producto.getImagen());
    }

    @Override
    public void actualizarProducto(Producto producto) {
        String SQL = "UPDATE Productos SET nombre = ?, descripcion = ?, precio = ?, stock = ?, categoria_id = ?, imagen = ? WHERE id = ?";
        jdbcTemplate.update(SQL, producto.getNombre(), producto.getDescripcion(),
                producto.getPrecio(), producto.getStock(), producto.getCategoriaId(),
                producto.getImagen(), producto.getId());
    }

    @Override
    public void eliminarProducto(Long id) {
        String SQL = "DELETE FROM Productos WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }
}
