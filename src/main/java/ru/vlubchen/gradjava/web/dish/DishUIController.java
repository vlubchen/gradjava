package ru.vlubchen.gradjava.web.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.vlubchen.gradjava.model.Dish;
import ru.vlubchen.gradjava.repository.RestaurantRepository;
import ru.vlubchen.gradjava.to.DishTo;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
public class DishUIController extends AbstractDishController {

    @Autowired(required=false)
    private RestaurantRepository restaurantRepository;

    @Override
    @GetMapping
    public List<DishTo> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate day,
                       @RequestParam String restaurant,
                       @RequestParam String name,
                       @RequestParam int price) {

        super.create(new Dish(null, day,  restaurantRepository.getByName(restaurant), name, price));
    }

    @Override
    @GetMapping("/filter")
    public List<DishTo> getByDay(
            @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate day) {
        return day != null ? super.getByDay(day) : super.getAll();
    }
}