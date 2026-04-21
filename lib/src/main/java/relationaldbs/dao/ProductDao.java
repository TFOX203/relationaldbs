package relationaldbs.dao;


import relationaldbs.model.Product;



/**
 * @author Alexander
 * apr 10, 2026
 */
public interface ProductDao {
	
	public boolean insert(Product product);
	

	public boolean delete(long id);
	

	public void update(Product product);
	

	public Product find(long id);
	
}
	
	
