package cab.driver.app.service;

import cab.driver.app.dao.DaoService;
import cab.driver.app.model.Driver;
import cab.driver.app.model.Rating;
import cab.driver.app.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CabDriverService {
 @Autowired
 private DaoService daoService;

    public List<Driver> createDriverInformation(int size){
        List<Driver> drivers = new ArrayList<>();
         for (int i=0;i<size;i++){
             Driver driver = new Driver();
             driver.setName(NameGenerator.getName());
             driver.setAddress(AddressGenerator.getAddress());
             driver.setEmailId(EmailIdGenerator.getEmailId(driver.getName()));
             driver.setMobile(PhoneNumberGenerator.getPhoneNumber());
             drivers.add(driver);
         }
        return drivers;
    }


//    public void calculateAcceptedTrips(int driverId) {
//        List<Rating> ratings = new ArrayList<>();
//        long acceptedTrips = 0;
//        long cancledTrips =0;
//
//
//        for (Rating rating : ratings) {
//            if (rating.getDriverId() == driverId) {
//                acceptedTrips += rating.getAcceptedTrips();
//                long l = cancledTrips - acceptedTrips;
//                System.out.println(l);
//            }
//
//        }
//
//    }
}
