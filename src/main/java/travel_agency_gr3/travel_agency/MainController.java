package travel_agency_gr3.travel_agency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import travel_agency_gr3.travel_agency.DTOBuilder.CityDTOBuilder;
import travel_agency_gr3.travel_agency.Service.CityService;
import travel_agency_gr3.travel_agency.Service.TripService;
import travel_agency_gr3.travel_agency.repository.CountryRepo;
import travel_agency_gr3.travel_agency.users.Countries;
import travel_agency_gr3.travel_agency.users.CustomerRegistrationDto;
import travel_agency_gr3.travel_agency.users.UserExistsException;
import travel_agency_gr3.travel_agency.users.UserRegistrationService;

import javax.validation.Valid;

@Controller
public class MainController {
    @Autowired
    TripService tripService;

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    CityService cityService;
    @Autowired
    CountryRepo countryRepo;

    @Autowired
    CityDTOBuilder cityDTOBuilder;

    String promotion = "Promowane oferty";

    String notPromotion = "Pozostałe oferty";

    @GetMapping("/")
    public ModelAndView getIndex() {

        ModelAndView m = new ModelAndView();

        m.setViewName("index");

        m.addObject("promotion", promotion);

        m.addObject("promotiontrips", tripService.findPromotionTrips());

        m.addObject("notpromotion", notPromotion);

        m.addObject("trips", tripService.findNotPromotionTrips());

        return m;

    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/register")
    public String registerForm(Model model) {
        model.addAttribute("customerFormData", new CustomerRegistrationDto());
        model.addAttribute("countries", Countries.values());
        return "registerForm";
    }

    @PostMapping(value = "/register")
    public String registerEffect(@ModelAttribute(name = "customerFormData") @Valid CustomerRegistrationDto customerFormData, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("countries", Countries.values());
            return "registerForm";
        }
        try {
            userRegistrationService.registerUser(customerFormData);
        } catch (UserExistsException e) {
            model.addAttribute("userExistsException", e.getMessage());
            return "registerForm";
        }
        model.addAttribute("registrationData", customerFormData);
        return "registerEffect";
    }


//    @EventListener(ApplicationReadyEvent.class)
//    public void addTrip() {
//
//        Country country = new Country();
//        country.setName("Polska");
//        countryRepo.save(country);
//
//        cityService.createNewCity(country, "Szczecin");
//
//        TripDTO trip = new TripDTO();
//        trip.setNumberOfDays(8);
//        trip.setAddultPrice(2000d);
//        trip.setDestinationName(cityService.findCity("Szczecin"));
//        trip.setName("Wycieczka do Włoch");
//        trip.setPromotion(true);
//        tripService.updateTrip(trip);
//
//        cityService.createNewCity(country, "Stargard");
//
//        TripDTO trip2 = new TripDTO();
//        trip2.setNumberOfDays(8);
//        trip2.setAddultPrice(2000d);
//        City c =cityService.findCity("Stargard");
//        trip2.setDestinationName(c);
//        trip2.setName("Wycieczka na Majorrrę");
//        trip2.setPromotion(true);
//        tripService.updateTrip(trip2);
//
//        CityDTO city3 = new CityDTO();
//        city3.setName("Amsterdam");
//        cityService.updateCity(city3);
//        TripDTO trip3 = new TripDTO();
//        trip3.setNumberOfDays(8);
//        trip3.setAddultPrice(2000d);
//        trip3.setDestinationName(city3);
//        trip3.setName("Wycieczka do Grecji");
//        trip3.setPromotion(true);
//        tripService.updateTrip(trip3);
//    }

    @GetMapping("/trips")
    public ModelAndView getMain() {

        ModelAndView m = new ModelAndView();

        m.setViewName("trips");

        m.addObject("someText", promotion);

        m.addObject("trips", tripService.findPromotionTrips());

        return m;

    }
}