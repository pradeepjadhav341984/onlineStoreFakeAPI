package pojo;

public class Product {
private String title;
private double price;
private String description;
private String imageurl;
private String category;
//constructor
public Product(String title,double price,String description,String imageurl,String category)
{
	this.title=title;
	this.price=price;
	this.description=description;
	this.imageurl=imageurl;
	this.category=category;
	
}

//getter to setters methods
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getImage() {
	return imageurl;
}
public void setImage(String imageurl) {
	this.imageurl = imageurl;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
}
