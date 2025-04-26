package com.restaurant.restaurant.Services;

import com.restaurant.restaurant.DTO.restaurantRequestDTO;
import com.restaurant.restaurant.DTO.restaurantResponseDTO;
import com.restaurant.restaurant.DTO.restaurantUserInfo;
import com.restaurant.restaurant.Model.restaurantModel;
import com.restaurant.restaurant.Repository.restaurantRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.List;

@Service
public class restaurantServices {
    @Autowired
    public HttpServletRequest request;
    private final WebClient webClient;

    private  final restaurantRepo restaurantRepo;

    public restaurantServices(WebClient webClient, restaurantRepo restaurantRepo) {
        this.webClient = webClient;
        this.restaurantRepo = restaurantRepo;
    }



    public restaurantResponseDTO addRestaurant(restaurantRequestDTO restaurant) {
        String token = getToken();
        String  username= restaurant.getUsername();
        Boolean isTaken = webClient.get()
                .uri("http://localhost:8082/api/v1/auth/checkEmailAvailability/{username}", username)
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        String  email= restaurant.getEmail();
        Boolean isExsit = webClient.get()
                .uri("http://localhost:8082/api/v1/auth/emailavailable/{email}", email)
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if(isTaken == false && isExsit == false) {
            restaurantModel restaurantdata = new restaurantModel();
            restaurantdata.setAddress(restaurant.getAddress());
            restaurantdata.setUsername(restaurant.getUsername());
            restaurantdata.setName(restaurant.getName());
            restaurantdata.setEmail(restaurantdata.getEmail());
            restaurantdata.setPhone(restaurant.getPhone());
            restaurantdata.setCity(restaurant.getCity());
            restaurantdata.setState(restaurant.getState());
            restaurantdata.setZip(restaurant.getZip());
            restaurantdata.setPhotoUrl(restaurant.getPhotoUrl());
            restaurantRepo.save(restaurantdata);


            restaurantUserInfo restaurantData = new restaurantUserInfo();
            restaurantData.setUsername(username);
            restaurantData.setEmail(restaurant.getEmail());
            restaurantData.setFullName(restaurant.getName());
            restaurantData.setPhoneNumber(restaurant.getPhone());
            restaurantData.setPassword(restaurant.getPassword());
            restaurantData.setRole("RESTAURANT_OWNER");
            restaurantData.setLocation(restaurant.getAddress());

            String url = "http://localhost:8082/api/v1/auth/cusregister";

            webClient.post()
                    .uri(url)
                    .header("Authorization", "Bearer " + token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(restaurantData)
                    .retrieve()
                    .bodyToMono(String.class)
                    .subscribe();


            return new restaurantResponseDTO(restaurant.getName(),restaurant.getUsername(),restaurant.getAddress(),restaurant.getPhone(),"good",restaurant.getPhotoUrl());
        }
        return new restaurantResponseDTO(null,null,null,null,null,"This username already exist");
    }

    public String getToken() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String token = request.getHeader("Authorization");
            return token;
        }
        return null;
    }
    public List<restaurantModel> getAllRest() {
        return restaurantRepo.findAll();
    }

    public restaurantResponseDTO updateRestaurant(restaurantRequestDTO restaurant) {
        restaurantModel restaurantdata = new restaurantModel();
        restaurantdata.setAddress(restaurant.getAddress());
        restaurantdata.setName(restaurant.getName());
        restaurantdata.setEmail(restaurantdata.getEmail());
        restaurantdata.setPhone(restaurant.getPhone());
        restaurantdata.setCity(restaurant.getCity());
        restaurantdata.setState(restaurant.getState());
        restaurantdata.setZip(restaurant.getZip());
        restaurantRepo.save(restaurantdata);

        return new restaurantResponseDTO(restaurant.getName(),restaurant.getUsername(),restaurant.getAddress(),restaurant.getPhone(),"good",restaurant.getPhotoUrl());
    }

    public void deleteById(Long id) {
        restaurantRepo.deleteByIdDetais(id);
    }

    public restaurantResponseDTO getUserByIdDetails(Long id) {
        restaurantModel restaurantdata = restaurantRepo.findById(id);
        return new restaurantResponseDTO(restaurantdata.getName(),restaurantdata.getUsername(),restaurantdata.getAddress(),restaurantdata.getPhone(),"good",restaurantdata.getPhotoUrl());
    }

}
