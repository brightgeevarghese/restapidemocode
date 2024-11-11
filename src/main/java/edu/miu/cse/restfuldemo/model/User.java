package edu.miu.cse.restfuldemo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;
    @Column(unique = true)
    private String username;//Do validation at dto, not here
    private String password;
}
