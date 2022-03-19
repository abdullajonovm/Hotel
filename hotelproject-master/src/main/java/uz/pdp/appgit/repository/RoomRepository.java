package uz.pdp.appgit.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appgit.entity.Room;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Integer> {

   @Query("select r from Room r where r.hotel.id = ?1")
   Page<Room>findAllByHotelId(Integer id,Pageable pageable);
}
