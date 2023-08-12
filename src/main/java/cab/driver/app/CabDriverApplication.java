package cab.driver.app;

import cab.driver.app.dao.DaoService;
import cab.driver.app.model.Driver;
import cab.driver.app.model.Rating;
import cab.driver.app.service.CabDriverService;
import cab.driver.app.service.CabRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CabDriverApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(CabDriverApplication.class, args);
    }
    @Autowired
    private DaoService daoService;
    @Autowired
    private CabDriverService cabDriverService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("welcome to spring boot app!");

      List<Driver> drivers = cabDriverService.createDriverInformation(100);
      daoService.insertDrivers(drivers);
      List<Driver> dbDrivers= daoService.getAllDrivers();
      daoService.insertRating(dbDrivers);

    }
}
