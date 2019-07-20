package travel_agency_gr3.travel_agency.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import travel_agency_gr3.travel_agency.DTO.CityDTO;
import travel_agency_gr3.travel_agency.DTO.CountryDTO;
import travel_agency_gr3.travel_agency.DTO.TripDTO;
import travel_agency_gr3.travel_agency.Service.CityService;
import travel_agency_gr3.travel_agency.Service.CountryService;
import travel_agency_gr3.travel_agency.Service.TripService;
import travel_agency_gr3.travel_agency.entity.FoodType;


import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/admin")
public class AdminContoller {
    @Autowired
    TripService tripService;

    @Autowired
    CityService cityService;

    @Autowired
    CountryService countryService;

    @GetMapping(value = "/addtrip")
    public String addTripForm(Model model) {
        model.addAttribute("tripFormData", new TripDTO());
        model.addAttribute("foodtypes", FoodType.values());
        model.addAttribute("cities",cityService.findAllCity());
        return "addTripForm";
    }

    @PostMapping(value = "/addtrip")
    public String addTrip(@ModelAttribute(name = "tripFormData") @Valid TripDTO trip, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("foodtypes",  FoodType.values());
            return "addTripForm";
        }
        LocalDate start = LocalDate.parse(trip.getDateOfDeparture());
        LocalDate rtn = LocalDate.parse(trip.getDateOfReturn());
        trip.setNumberOfDays(rtn.getDayOfYear() - start.getDayOfYear());
        tripService.updateTrip(trip);
        model.addAttribute("addTripData", trip.getName());
        return "addTripEffect";
    }

    @GetMapping(value = "/addcountry")
    public String addCountryForm(Model model) {
        model.addAttribute("countryFormData", new CountryDTO());
        return "addCountryForm";
    }

    @PostMapping(value = "/addcountry")
    public String addCountry(@ModelAttribute(name = "countryFormData") @Valid CountryDTO countryDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "addCountryForm";
        }
        countryService.updateCountry(countryDTO);
        model.addAttribute("addCountryData", countryDTO.getName());
        return "addCountryEffect";
    }
    @GetMapping(value = "/addcity")
    public String addCityForm(Model model) {
        model.addAttribute("cityFormData", new CityDTO());
        model.addAttribute("countries",countryService.findAllCountry());
        return "addCityForm";
    }

    @PostMapping(value = "/addcity")
    public String addCity(@ModelAttribute(name = "cityFormData") @Valid CityDTO cityDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "addCityForm";
        }
        cityService.updateCity(cityDTO);
        model.addAttribute("addCityData", cityDTO.getName());
        return "addCityEffect";
    }
}

