package com.example.demo;

import com.example.demo.Entities.Restaurant;
import com.example.demo.Entities.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService {
    private final RestaurantRepo restaurantRepo;
    private final UserService userService;
    public RestaurantService(RestaurantRepo restaurantRepo, UserService userService) {
        this.restaurantRepo = restaurantRepo;
        this.userService = userService;
    }

    public void registerRestaurant(@Valid RestaurantDTO restaurant) throws NullPointerException{
        Restaurant restaurant1 = new Restaurant();
        Optional<User> owner = userService.findUserById(restaurant.owner_id());
        if (owner.isEmpty()) {
            throw new NullPointerException("User not found");
        }
        restaurant1.setOwner(owner.get());
        restaurant1.setCnpj(restaurant.cnpj());
        restaurant1.setLocal(restaurant.local());
        restaurant1.setName(restaurant.name());
        restaurantRepo.save(restaurant1);
    }
    // mudar dps pra changeRestaurantProperty e abstrai pra nao ter q fazer 200 metodos
    public void changeRestaurantName(String cnpj, String newname) throws NullPointerException {
        Optional<Restaurant> restaurant = restaurantRepo.findByCnpj(cnpj);
        if (restaurant.isEmpty()) {
            throw new NullPointerException();
        }
        restaurant.get().setName(newname);
        restaurantRepo.save(restaurant.get());
    }
}
