package testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import java.util.Map;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import pojo.Product;
import routers.Routes;

public class ProductDataDriven extends BaseClass {

	@Test(dataProvider="jsonDataProvider",dataProviderClass=utils.DataProviders.class)
	public void testAddNewProduct(Map<String,String> data)
	{
		 String title=data.get("title");
		 double price=Double.parseDouble(data.get("price"));
		 String category=data.get("category");
		 String description=data.get("description");
		 String image=data.get("image");
		// .cateory.String title,double price,String description,String imageurl,String category
		 Product newProduct=new Product(title, price, description, image,category);
		 
			int productID=given()	
		    .contentType(ContentType.JSON)
		    .body(newProduct)
			.when()
			.post(Routes.CREATE_PRODUCT)
		    .then()
		    .statusCode(200)
			.log().body()
			.body("id",notNullValue())
			.body("title", equalTo(newProduct.getTitle()))
			.extract().jsonPath().getInt("id");
			System.out.println("ProductID==>"+productID);
			
			//delete the product
			given()
			.pathParam("id", productID)
			.when()
			.delete(Routes.DELETE_CART)
			.then()
			.statusCode(200);
			System.out.println("deleted  ProductID==>"+productID);
	}
}
