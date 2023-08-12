package cab.driver.app.service;

import cab.driver.app.model.Driver;
import cab.driver.app.model.Rating;
import cab.driver.app.utils.AcceptedTripsGenerator;
import cab.driver.app.utils.TotalTripsGenerator;

import java.util.ArrayList;
import java.util.List;

public class CabRatingService {
    public List<Rating> createDriverRatingInformation(List<Driver> drivers){
        List<Rating> ratings = new ArrayList<>();
        for(Driver driver : drivers){
            Rating rating = new Rating();
            rating.setDriverId(driver.getId());
            rating.setTrips(TotalTripsGenerator.getTotalTrips());
            rating.setAcceptedTrips(AcceptedTripsGenerator.getAcceptedTrips());
            rating.setCancledtrips(rating.getTrips()-rating.getAcceptedTrips());
            ratings.add(rating);
        }
        return ratings;
    }
}
