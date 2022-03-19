package uz.pdp.appgit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.pdp.appgit.entity.Hotel;
import uz.pdp.appgit.entity.Room;
import uz.pdp.appgit.payload.RoomDto;
import uz.pdp.appgit.repository.HotelRepository;
import uz.pdp.appgit.repository.RoomRepository;
import uz.pdp.appgit.response.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;
    public ApiResponse getAll(){
        List<Room> all = roomRepository.findAll();
        return new ApiResponse("List of rooms",true,all);
    }

    public ApiResponse addRoom(RoomDto dto){
        if (hotelRepository.existsById(dto.getHotelId())) {
            Optional<Hotel> byId = hotelRepository.findById(dto.getHotelId());
            Hotel hotel = byId.get();
            Room room=new Room();
            room.setFloor(dto.getFloor());
            room.setNumber(dto.getNumber());
            room.setSize(dto.getSize());
            room.setHotel(hotel);
            Room save = roomRepository.save(room);
            return new ApiResponse("Added successfully",true,save);

        }
        return new ApiResponse("Hotel not found",false);
    }
    public ApiResponse editRoom(Integer id,RoomDto dto){
        if (roomRepository.existsById(id)) {
            Optional<Room> byId = roomRepository.findById(id);
            Room room = byId.get();
            room.setNumber(dto.getNumber());
            room.setSize(dto.getSize());
            room.setFloor(dto.getFloor());
            room.setHotel(hotelRepository.findById(dto.getHotelId()).get());
            Room save = roomRepository.save(room);
            return new ApiResponse("Found and updated",true,save);
        }
        return new ApiResponse("Room does not exist with such id",false);
    }
    public ApiResponse getOne(Integer id){
        if (roomRepository.existsById(id)) {
            Room room = roomRepository.findById(id).get();
            return new ApiResponse("Room ",true,room);
        }
        return new ApiResponse("Room does not exits",false);
    }
//    public Page<Room> getRoomPages(Integer id){
//        Page<Room> allByHotelId = roomRepository.findAllByHotelId(id);
//        return allByHotelId;
//    }
    public ApiResponse deleteRoom(Integer id){
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
            return new ApiResponse("Found and deleted",true);
        }
        return new ApiResponse("Room with such id does not exist",false);
    }
}
