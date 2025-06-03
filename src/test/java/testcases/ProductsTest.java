package testcases;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import java.util.List;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Payload;
import pojo.Product;
import routers.Routes;

public class ProductsTest extends BaseClass{

	 @Test(priority=1,enabled=true,description="get all products")
	public void tetsGetAllProducts()
	{
		given()
		.when()
		.get(Routes.GET_All_PRODUTS)
	    .then()
	    .statusCode(200)
		.body("size()",greaterThan(0))
		.log().body(); //we can print all body using logger
	}
	 
	 @Test(priority=2,enabled=true,description="get the single products by ID")
		public void tetsGetSingleProductByID()
		{
		 
		    int productID=configReader.getIntProperty("productID");
			given()
			.pathParam("id", productID)
			.when()
			.get(Routes.GET_PRODUCT_BY_ID)
		    .then()
		    .statusCode(200)
		    .log().body();
			
		}
	 
	 @Test(priority=3,enabled=true,description="get the  products by limit")
		public void tetsGetProductByLimit()
		{
		 
			given()
			.pathParam("limit", 3)
			.when()
			.get(Routes.GET_PRODUCT_WITH_LIMIT)
		    .then()
		    .statusCode(200)
		    .log().body()
		    .body("size()",equalTo(3));
			
		}
	 
	 @Test(priority=4,enabled=true,description="get the  products by sort decending order")
		public void tetsGetProductBySortedDending()
		{
		 
		Response response=	given()
				.pathParam("order", "desc")
			.when()
			.get(Routes.GET_PRODUCT_SORTED)
		    .then()
		    .statusCode(200)
		    .log().body()
		    .extract().response();
		   List<Integer> ProductIDs=response.jsonPath().getList("id",Integer.class);
		   assertThat("Product IDs are not in descending order", isSortedDecending(ProductIDs), is(true));
		}
	 @Test(priority=5,enabled=true,description="get the  products by sort decending order")
		public void tetsGetProductBySortedAscending()
		{
		 
		Response response=	given()
				.pathParam("order", "asec")
			.when()
			.get(Routes.GET_PRODUCT_SORTED)
		    .then()
		    .statusCode(200)
		    .log().body()
		    .extract().response();
		   List<Integer> ProductIDs=response.jsonPath().getList("id",Integer.class);
		   assertThat("Product IDs are not in descending order", isSortedAscending(ProductIDs), is(true));
		}
		   
	 @Test(priority=6,enabled=true,description="get the  all categories ")
		public void tetsGettheAllCategories()
		{
		 
			given()		
			.when()
			.get(Routes.GET_ALL_CATEGORIES)
		    .then()
		    .statusCode(200)
		    .log().body()
		    .body("size()", greaterThan(0));
		 
		}

	 @Test(priority=7,enabled=true,description="get the  all by  categories ")
		public void tetsGettheAllByCategories()
		{
		 
			given()	
			.pathParam("category","electronics")
			.when()
			.get(Routes.GET_PRODUCT_BY_CATEGORIES)
		    .then()
		    .statusCode(200)
		     .body("size()", greaterThan(0))
		    .body("category",everyItem(notNullValue()))
			.body("category",everyItem(equalTo("electronics")))
			.log().body();
					
		 
		}

	 @Test(priority=8,enabled=true,description="add new products ")
		public void tetsAddnewProduct()
		{
		  Product newProduct=Payload.productPayload();
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
			System.out.println(productID);
					
		 
		}
	 @Test(priority=9,enabled=true,description="update the existing product ")
		public void tetsUpdateProduct()
		{
		 int productID=configReader.getIntProperty("productID");
		 Product updatedPayload=Payload.productPayload();
			given()	
		    .contentType(ContentType.JSON)
		    .body(updatedPayload)
		    .pathParam("id", productID)
			.when()
			.put(Routes.UPDATE_PRODUCT)
		    .then()
		    .statusCode(200)
			.log().body()
			.body("title", equalTo(updatedPayload.getTitle()));
			
					
		 
		}
	 
	 @Test(priority=10,enabled=true,description="delete the existing product ")
		public void tetsDeleteProduct()
		{
		 int productID=configReader.getIntProperty("productID");
			given()	
		    .pathParam("id", productID)
			.when()
			.put(Routes.DELETE_PRODUCT)
		    .then()
		    .statusCode(200)
		    .log().body();
			
			
					
		 
		}
	 
	}
	
	

