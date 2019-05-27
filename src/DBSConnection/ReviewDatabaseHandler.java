package DBSConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Product;
import model.Review;

public class ReviewDatabaseHandler implements StoreReviewPersistence
{

   private DBSQuery query;

   public ReviewDatabaseHandler(DBSQuery query)
   {
      this.query = query;
   }

   @Override
   public ArrayList<Review> loadReviews(ArrayList<Product> products)
         throws SQLException
   {

      return new ArrayList<Review>(query.map(
            "SELECT review_id, message, rating, product_id FROM Review;",
            statement -> {
            }, resultSet -> {
               Product product = null;
               for (int i = 0; i < products.size(); i++)
               {
                  if (products.get(i).getID() == resultSet.getInt("product_id"))
                  {
                     product = products.get(i);
                     break;
                  }
               }
               return new Review(resultSet.getInt("review_ID"),
                     resultSet.getDouble("rating"),
                     resultSet.getString("message"), product);
            }));
   }

   @Override
   public void addReview(Review review) throws SQLException
   {
      query.executeUpdate(
            "INSERT INTO Review (review_id, message, rating, product_id) VALUES (?, ?, ?, ?);",
            statement -> {
               statement.setInt(1, review.getID());
               statement.setString(2, review.getMessage());
               statement.setDouble(3, review.getRating());
               statement.setInt(4, review.getProduct().getID());
            });

   }

   @Override
   public void removeReveiew(Review review) throws SQLException
   {
      query.executeUpdate("DELETE FROM Review WHERE review_id = ?;",
            statement -> {
               statement.setInt(1, review.getID());
            });
   }

   @Override
   public void clearReviews() throws SQLException
   {
      query.executeUpdate("DELETE FROM Review;", statement -> {
      });
   }

}
