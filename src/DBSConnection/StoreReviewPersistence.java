package DBSConnection;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Product;
import model.Review;

public interface StoreReviewPersistence {
	
	ArrayList<Review> loadReviews(ArrayList<Product> products) throws SQLException;
	
	void addReview(Review review) throws SQLException;
	
	void removeReveiew(Review review) throws SQLException;
	
	void clearReviews() throws SQLException;
}
