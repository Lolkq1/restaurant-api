package com.example.demo.Services;

import com.example.demo.Entities.Restaurant;
import com.example.demo.Entities.User;
import com.example.demo.Repositories.RestaurantRepo;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CNPJ;
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
    //
    public void registerRestaurant(@Valid @CNPJ String cnpj, String name, String local, Long id) throws NullPointerException {
        Optional<User> owner = userService.findUserById(id);
        if (owner.isEmpty()) {
            throw new NullPointerException("User not found");
        }
        restaurantRepo.save(new Restaurant(cnpj, name, local, owner.get()));
    }
    // mudar dps pra changeRestaurantProperty e abstrai pra nao ter q fazer 200 metodos
    // colocar criterio p nome do restaurante dps
    public void changeRestaurantName(String cnpj, String newname) throws NullPointerException {
        Optional<Restaurant> restaurant = restaurantRepo.findByCnpj(cnpj);
        if (restaurant.isEmpty()) {
            throw new NullPointerException();
        }
        restaurant.get().setName(newname);
        restaurantRepo.save(restaurant.get());
    }

}
