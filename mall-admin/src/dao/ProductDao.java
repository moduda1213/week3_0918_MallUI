package dao;
import java.sql.*;
import java.util.*;
import vo.*;

public class ProductDao {
	public ArrayList<Product> selectProductList() throws Exception{
		ArrayList<Product> list = new ArrayList<Product>();
		
		String driver ="org.mariadb.jdbc.Driver";
		String dbaddr ="jdbc:mariadb://localhost:3306/mall";
		String dbid="root";
		String dbpw="java1004";
		String sql = "select product_id, category_id, product_name, product_price, product_content,product_soldout from product";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr,dbid,dbpw); 
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Product p = new Product();
			p.productId = rs.getInt("product_id");
			p.categoryId = rs.getInt("category_id");
			p.productName = rs.getString("product_name");
			p.productPrice = rs.getInt("product_price");
			p.productContent = rs.getString("product_content");
			p.productSoldout = rs.getString("product_soldout");
			list.add(p);
		}
		conn.close();
		return list;
	}

	public ArrayList<Product> selectProductListByCategory(int categoryId) throws Exception{
		ArrayList<Product> list = new ArrayList<Product>();
		
		String driver ="org.mariadb.jdbc.Driver";
		String dbaddr ="jdbc:mariadb://localhost:3306/mall";
		String dbid="root";
		String dbpw="java1004";
		String sql = "select product_id, category_id, product_name, product_price, product_content,product_soldout from product where category_id=?";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr,dbid,dbpw); 
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, categoryId);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Product p = new Product();
			p.productId = rs.getInt("product_id");
			p.categoryId = rs.getInt("category_id");
			p.productName = rs.getString("product_name");
			p.productPrice = rs.getInt("product_price");
			p.productContent = rs.getString("product_content");
			p.productSoldout = rs.getString("product_soldout");
			list.add(p);
		}
		conn.close();
		return list;
	}
	//productList 추가
	public void insertProduct(Product product) throws Exception{
		String driver ="org.mariadb.jdbc.Driver";
		String dbaddr ="jdbc:mariadb://localhost:3306/mall";
		String dbid="root";
		String dbpw="java1004";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr,dbid,dbpw);
		String sql = "insert into product(category_id,product_name,product_price, product_content,product_soldout) values(?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, product.categoryId);
		stmt.setString(2, product.productName);
		stmt.setInt(3, product.productPrice);
		stmt.setString(4, product.productContent);
		stmt.setString(5, product.productSoldout);
		stmt.executeUpdate();
		
		conn.close();
	}
	
	//productList 삭제
	public void deleteProduct(Product product) throws Exception{
		String driver ="org.mariadb.jdbc.Driver";
		String dbaddr ="jdbc:mariadb://localhost:3306/mall";
		String dbid="root";
		String dbpw="java1004";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr,dbid,dbpw);
		String sql = "delete from product where product_id =?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, product.productId);
		stmt.executeUpdate();
		
		conn.close();
	}
	
	//productList 수정
	public void updateProduct(Product product) throws Exception{
		String driver ="org.mariadb.jdbc.Driver";
		String dbaddr ="jdbc:mariadb://localhost:3306/mall";
		String dbid="root";
		String dbpw="java1004";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr,dbid,dbpw);
		String sql = "update product set category_id=?, product_name=?, product_price=?,product_content=?,product_soldout=? where product_id =?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, product.categoryId);
		stmt.setString(2,product.productName);
		stmt.setInt(3,product.productPrice);
		stmt.setString(4,product.productContent);
		stmt.setString(5,product.productSoldout);
		stmt.setInt(6, product.productId);
		stmt.executeUpdate();
		
		conn.close();
		
	}
	
	//productList 수정(강사님 version)
	public void updateProductSoldout(int productId, String productSoldout) throws Exception {
		String driver ="org.mariadb.jdbc.Driver";
		String dbaddr ="jdbc:mariadb://localhost:3306/mall";
		String dbid="root";
		String dbpw="java1004";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr,dbid,dbpw);
		String sql = "update product set product_soldout=? where product_id =?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		if(productSoldout.equals("Y")) {
			stmt.setString(1,"N");
		}else {
			stmt.setString(1,"Y");
		}
		stmt.setInt(2, productId);
		//stmt.executeUpdate();
		//디버깅 과정
		int row = stmt.executeUpdate();
		System.out.println(row+"행 수정");
	}
	
	//product 상세보기
	public Product selectProductOne(int productId) throws Exception{
		Product product = null;
		
		String driver ="org.mariadb.jdbc.Driver";
		String dbaddr ="jdbc:mariadb://localhost:3306/mall";
		String dbid="root";
		String dbpw="java1004";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr,dbid,dbpw);
		String sql = "select * from product where product_id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, productId);
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			product = new Product();
			product.productId = rs.getInt("product_id");
			product.categoryId = rs.getInt("category_id");
			product.productName = rs.getString("product_name");
			product.productPrice = rs.getInt("product_price");
			product.productContent = rs.getString("product_content");
			product.productSoldout = rs.getString("product_soldout");
		}
		
		return product;
	}
	
	// 정렬시키기
	
	
	
	
	
	
	
}
