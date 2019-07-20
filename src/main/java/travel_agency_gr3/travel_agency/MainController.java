package travel_agency_gr3.travel_agency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import travel_agency_gr3.travel_agency.DTO.TripDTO;
import travel_agency_gr3.travel_agency.DTOBuilder.CityDTOBuilder;
import travel_agency_gr3.travel_agency.Service.CityService;
import travel_agency_gr3.travel_agency.Service.TripService;
import travel_agency_gr3.travel_agency.entity.City;
import travel_agency_gr3.travel_agency.entity.Country;
import travel_agency_gr3.travel_agency.entity.FoodType;
import travel_agency_gr3.travel_agency.entity.Trip;
import travel_agency_gr3.travel_agency.repository.CountryRepo;
import travel_agency_gr3.travel_agency.users.Countries;
import travel_agency_gr3.travel_agency.users.CustomerRegistrationDto;
import travel_agency_gr3.travel_agency.users.UserExistsException;
import travel_agency_gr3.travel_agency.users.UserRegistrationService;

import javax.validation.Valid;
import java.time.LocalDate;

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
    @GetMapping("/index")
    public ModelAndView getIndexView() {
      return getIndex();
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

    @GetMapping("/trips")
    public ModelAndView getMain() {

        ModelAndView m = new ModelAndView();

        m.setViewName("trips");

        m.addObject("someText", promotion);

        m.addObject("trips", tripService.findPromotionTrips());

        return m;

    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void addTrip() {
//
//        Country polska = new Country();
//        polska.setName("Polska");
//        countryRepo.save(polska);
//        cityService.createNewCity(polska, "Szczecin");
//        cityService.createNewCity(polska, "Stargard");
//
//        Country grecja = new Country();
//        grecja.setName("Grecja");
//        countryRepo.save(grecja);
//        cityService.createNewCity(grecja, "Ateny");
//        cityService.createNewCity(grecja, "Saloniki");
//
//        Country bulgaria = new Country();
//        bulgaria.setName("Bułgaria");
//        countryRepo.save(bulgaria);
//        cityService.createNewCity(bulgaria, "Warna");
//        cityService.createNewCity(bulgaria, "Sofia");
//
//        Country chorwacja = new Country();
//        chorwacja.setName("Chorwacja");
//        countryRepo.save(chorwacja);
//        cityService.createNewCity(chorwacja, "Zagrzeb");
//        cityService.createNewCity(chorwacja, "Dubrownik");
//
//        Country hiszpania = new Country();
//        hiszpania.setName("Hiszpania");
//        countryRepo.save(hiszpania);
//        cityService.createNewCity(hiszpania, "Palma de Mallorca");
//        cityService.createNewCity(hiszpania, "Barcelona");
//
//        Country portugalia = new Country();
//        portugalia.setName("Portugalia");
//        countryRepo.save(portugalia);
//        cityService.createNewCity(portugalia, "Porto");
//        cityService.createNewCity(portugalia, "Lizbona");
//
//        tripService.createNewTrip("Wycieczka do Szczecina",cityService.findCityNyName("Szczecin"), LocalDate.parse("2019-10-25"),LocalDate.parse("2019-10-31"), FoodType.AL,200d,100d,4,2,false);
//        tripService.createNewTrip("Wycieczka do Stargardu",cityService.findCityNyName("Stargard"), LocalDate.parse("2019-10-20"),LocalDate.parse("2019-10-30"), FoodType.BB,100d,10d,5,1,true);
//        tripService.createNewTrip("Wycieczka do Grecji",cityService.findCityNyName("Ateny"), LocalDate.parse("2019-08-15"),LocalDate.parse("2019-08-25"), FoodType.HB,300d,120d,4,4,true);
//        tripService.createNewTrip("Wizyta na Salonach",cityService.findCityNyName("Saloniki"), LocalDate.parse("2019-07-15"),LocalDate.parse("2019-07-25"), FoodType.FB,80d,40d,2,2,false);
//        tripService.createNewTrip("W ciepłe kraje",cityService.findCityNyName("Warna"), LocalDate.parse("2019-07-15"),LocalDate.parse("2019-08-02"), FoodType.AL,160d,85d,3,2,true);
//        tripService.createNewTrip("Wycieczka do Sofii",cityService.findCityNyName("Sofia"), LocalDate.parse("2019-09-15"),LocalDate.parse("2019-09-26"), FoodType.FB,60d,10d,2,1,false);
//        tripService.createNewTrip("Wycieczka do Bulgarii",cityService.findCityNyName("Sofia"), LocalDate.parse("2019-09-15"),LocalDate.parse("2019-09-26"), FoodType.FB,60d,10d,4,0,true);
//        tripService.createNewTrip("Odwiedziny w Dubrowniku",cityService.findCityNyName("Dubrownik"), LocalDate.parse("2019-09-05"),LocalDate.parse("2019-10-15"), FoodType.AL,100d,100d,3,2,false);
//        tripService.createNewTrip("Odwiedziny w Porto",cityService.findCityNyName("Porto"), LocalDate.parse("2019-09-25"),LocalDate.parse("2019-10-15"), FoodType.AL,110d,100d,2,2,false);
//        tripService.createNewTrip("Lizbona",cityService.findCityNyName("Lizbona"), LocalDate.parse("2019-09-15"),LocalDate.parse("2019-10-10"), FoodType.AL,130d,100d,32,2,false);
//        tripService.createNewTrip("Spotkajmy sie w Barcelonie",cityService.findCityNyName("Barcelona"), LocalDate.parse("2019-09-09"),LocalDate.parse("2019-10-13"), FoodType.AL,150d,1d,0,4,false);
//        tripService.createNewTrip("Na Majorke",cityService.findCityNyName("Palma de Mallorca"), LocalDate.parse("2019-09-06"),LocalDate.parse("2019-10-14"), FoodType.AL,125d,55d,2,5,false);
//
//    }
}