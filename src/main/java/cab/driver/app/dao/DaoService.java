package cab.driver.app.dao;

import cab.driver.app.model.Driver;
import cab.driver.app.model.Rating;
import cab.driver.app.service.CabDriverService;
import cab.driver.app.service.CabRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DaoService {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void createDriverTable() {
        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS driver (" +
                    "driver_id SERIAL PRIMARY KEY ," +
                    "name VARCHAR(255)," +
                    "address VARCHAR(255) ," +
                    "mobile bigint," +
                    "email_id VARCHAR(255) " +
                    ")";
            statement.executeUpdate(query);
            System.out.println("Table created Successfully");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createRatingTable() {
        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS rating (" +
                    "id SERIAL PRIMARY KEY," +
                    "driver_id bigint," +
                    "trips bigint," +
                    "accepted_trips bigint," +
                    "cancled_trips bigint," +
                    "FOREIGN KEY (driver_id) REFERENCES driver(driver_id)" +
                    ")";
            statement.executeUpdate(query);
            System.out.println("Table created successfully");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertDrivers(List<Driver> drivers) {
        drivers.forEach(driver -> insertDriver(driver));
    }

    public void insertDriver(Driver driver) {
        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();
            String query = "INSERT INTO driver(name,address,mobile,email_id) VALUES (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            int index = 1;
            ps.setString(index++, driver.getName());
            ps.setString(index++, driver.getAddress());
            ps.setInt(index++, driver.getMobile());
            ps.setString(index, driver.getEmailId());
            int id = ps.executeUpdate();

            ps.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRating(List<Driver> drivers) {
        CabRatingService crs = new CabRatingService();
        List<Rating> ratings = crs.createDriverRatingInformation(drivers);
        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();
            String query = "INSERT INTO rating(driver_Id,trips,accepted_Trips,cancled_trips) VALUES (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            for (Rating rating : ratings) {
                int index = 1;
                ps.setInt(index++, rating.getDriverId());
                ps.setLong(index++, rating.getTrips());
                ps.setLong(index++, rating.getAcceptedTrips());
                ps.setLong(index, rating.getCancledtrips());
                ps.executeUpdate();
            }
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Driver> getAllDrivers() {
        // select * from driver
        // return collection of Driver;
        ArrayList<Driver> drivers = new ArrayList<>();
        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from driver");
            while (rs.next()) {
                Driver driver = new Driver();
                driver.setId(rs.getInt("driver_Id"));
                driver.setName(rs.getString("name"));
                driver.setAddress(rs.getString("address"));
                driver.setEmailId(rs.getString("email_id"));
                driver.setMobile(rs.getInt("mobile"));
                drivers.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }
}