package exercise.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import exercise.model.Product;

import java.sql.SQLException;
import java.sql.Statement;

public class ProductsRepository extends BaseRepository {
    private static final String SAVE_SQL = "INSERT INTO products (title, price) VALUES (?, ?)";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM products WHERE id = ?";
    private static final String GET_ALL_SQL = "SELECT * FROM products ORDER BY id";

    // BEGIN
    public static void save(Product product) throws SQLException {
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getTitle());
            statement.setInt(2, product.getPrice());
            statement.executeUpdate();

            var key = statement.getGeneratedKeys();

            if (key.next()) {
                var id = key.getLong("id");
                product.setId(id);
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static Optional<Product> find(Long id) throws SQLException {
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            var result = statement.executeQuery();

            if (result.next()) {
                var title = result.getString("title");
                var price = result.getInt("price");
                var product = new Product(title, price);
                product.setId(id);
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    public static List<Product> getEntities() throws SQLException {
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(GET_ALL_SQL)) {
            var list = new ArrayList<Product>();
            var result = statement.executeQuery();

            while (result.next()) {
                var id = result.getLong("id");
                var title = result.getString("title");
                var price = result.getInt("price");
                var product = new Product(title, price);
                product.setId(id);
                list.add(product);
            }
            return list;
        }
    }
// END
}
