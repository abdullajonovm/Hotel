package uz.pdp.appgit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appgit.entity.Hotel;
import uz.pdp.appgit.entity.Room;
import uz.pdp.appgit.repository.RoomRepository;
import uz.pdp.appgit.response.ApiResponse;
import uz.pdp.appgit.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelService hotelService;
    @Autowired
    RoomRepository repository;
    @GetMapping
    public ApiResponse all(){
        ApiResponse all = hotelService.getAll();
        return all;
    }
    @PostMapping
    public ApiResponse add(@RequestBody Hotel hotel){
        ApiResponse apiResponse = hotelService.addHotel(hotel);
        return apiResponse;
    }
    @PutMapping("/{id}")
    public ApiResponse one(@PathVariable Integer id,@RequestBody Hotel hotel){
        ApiResponse apiResponse = hotelService.editHotel(id,hotel);
        return apiResponse;
    }
    @GetMapping("/{id}")
    public ApiResponse one(@PathVariable Integer id){
        ApiResponse apiResponse = hotelService.getOne(id);
        return apiResponse;
    }
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        ApiResponse apiResponse = hotelService.deleteHotel(id);
        return apiResponse;
    }
    @GetMapping("/getrooms/{id}")// hotel id orqali room page olish
    public Page<Room> getRoomsByHotelsId(@PathVariable Integer id,@RequestParam("page") Integer page){
        Pageable pageable=PageRequest.of(page,5);
        Page<Room> allByHotelId = repository.findAllByHotelId(id, pageable);
        return allByHotelId;

    }
}
