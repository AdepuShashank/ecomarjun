package Services;

//import java.util.ArrayList;
//import java.util.List;

import org.springframework.stereotype.Service;

import models.Product;
@Service
public class ProductService {
	
	
	public Product GetProduct(int id){
		Product prfdb = new Product ("keyboards",1,234.4, "iodevices");
		
		return prfdb;
	}
	
	

}
