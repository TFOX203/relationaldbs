package relationaldbs.dao;

import relationaldbs.model.Product;

/**
 * @author __Alexander__
 * apr 10, 2026
 * 
 */
public interface ProductDao {
	/**
	 *  Interfaz que define las operaciones 
	 * @param product
	 * @return
	 */
    
    public boolean insert(Product product);
    /**
     * Inserta un nuevo producto en la base de datos.
     * @param product objeto Product con los datos a insertar
     * @return true si se insertó correctamente, false si hubo algún error
     */
    
    public boolean delete(long id);
    /**
     * Elimina un producto de la base de datos por su id.
     * @param id identificador único del producto a eliminar
     * @return true si se eliminó correctamente, false si no se encontró o hubo error
     */
    
    public void update(Product product);
    /**
     * Actualiza los datos de un producto existente en la base de datos.
     * El producto se identifica por su id.
     * @param product objeto Product con los nuevos datos a actualizar
     */
    
    public Product find(long id);
    /**
     * Busca y devuelve un producto de la base de datos por su id.
     * @param id identificador único del producto a buscar
     * @return el objeto Product si se encontró, null si no existe
     */
}