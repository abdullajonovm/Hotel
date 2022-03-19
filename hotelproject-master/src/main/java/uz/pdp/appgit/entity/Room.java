package uz.pdp.appgit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,name = "roomNumber")
    private Integer number;
    private Integer floor;
    private Integer size;
    @ManyToOne
    private Hotel hotel;
}
