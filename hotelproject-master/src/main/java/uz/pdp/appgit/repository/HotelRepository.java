package uz.pdp.appgit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appgit.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {
    @Query("select (count(h) > 0) from Hotel h where h.name = ?1")
    boolean existsByName(String name);
}
