package uz.pdp.appgit.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDto {
    private Integer number;
    private Integer floor;
    private Integer size;
    private Integer hotelId;
}
