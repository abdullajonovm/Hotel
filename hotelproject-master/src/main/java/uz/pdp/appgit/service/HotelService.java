package uz.pdp.appgit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appgit.entity.Hotel;
import uz.pdp.appgit.repository.HotelRepository;
import uz.pdp.appgit.response.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    HotelRepository hotelRepository;
    public ApiResponse getAll(){
        List<Hotel> all = hotelRepository.findAll();
        return new ApiResponse("List of Hotels",true,all);
    }
    public ApiResponse addHotel(Hotel myhotel){
        if (!hotelRepository.existsByName(myhotel.getName())) {
            Hotel hotel=new Hotel();
            hotel.setName(myhotel.getName());
            Hotel save = hotelRepository.save(hotel);
            return new ApiResponse("Addes successfully",true,save);
        }
        return new ApiResponse("Hotel with such name already exists",false);

    }
    public ApiResponse editHotel(Integer id,Hotel myhotel){
        if (hotelRepository.existsById(id)) {
            Optional<Hotel> byId = hotelRepository.findById(id);
            Hotel hotel = byId.get();
            hotel.setName(myhotel.getName());
            Hotel save = hotelRepository.save(hotel);
            return new ApiResponse("Found and updated",true,save);
        }
        return new ApiResponse("Hotel not found",false);
    }
    public ApiResponse getOne(Integer id){
        if (hotelRepository.existsById(id)) {
            Optional<Hotel> byId = hotelRepository.findById(id);
            Hotel hotel = byId.get();
            return new ApiResponse("Hotel",true,hotel);
        }
        return new ApiResponse("Hotel with such id does not exist",false);
    }
    public ApiResponse deleteHotel(Integer id){
        if (hotelRepository.existsById(id)) {
            hotelRepository.deleteById(id);
            return new ApiResponse("Found and deleted",true);
        }
        return new ApiResponse("Hotel with such id does not exist",false);
    }
}
