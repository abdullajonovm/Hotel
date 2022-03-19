package uz.pdp.appgit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appgit.payload.RoomDto;
import uz.pdp.appgit.response.ApiResponse;
import uz.pdp.appgit.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;
    @GetMapping
    public ApiResponse all(){
        ApiResponse apiResponse = roomService.getAll();
    return apiResponse;
    }
    @PostMapping
    public ApiResponse add(@RequestBody RoomDto dto){
        ApiResponse apiResponse = roomService.addRoom(dto);
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse editRoom(@PathVariable Integer id,@RequestBody RoomDto dto){
        ApiResponse apiResponse = roomService.editRoom(id, dto);
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        ApiResponse apiResponse = roomService.deleteRoom(id);
        return apiResponse;
    }
    @GetMapping("/{id}")
    public ApiResponse one(@PathVariable Integer id){
        ApiResponse one = roomService.getOne(id);
        return one;
    }

}
