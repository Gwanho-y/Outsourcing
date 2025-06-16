package entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 254, name = "email")
    private String email;

    @Column(nullable = false, length = 254)
    private String password;

    @Column(nullable = false, length = 5)
    private String name;

}
