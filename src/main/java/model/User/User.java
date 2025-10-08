package model.User;

import jakarta.persistence.*;
import lombok.*;
import model.Status.Status;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class User {

    public User(String name, int age, String email, LocalDateTime createdAt) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.createdAt = createdAt;
    }

    @Id
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @ToString.Include
    @EqualsAndHashCode.Include
    private String name;

    @Column(nullable = false)
    @ToString.Include
    @EqualsAndHashCode.Include
    private String email;

    @Column(nullable = false)
    @ToString.Include
    @EqualsAndHashCode.Include
    private Integer age;

    @Column(name = "created_at", nullable = false)
    @ToString.Include
    @EqualsAndHashCode.Include
    private LocalDateTime createdAt;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "status", nullable = false)
    Status status;
}
