package model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "status")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Status {

    public Status(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Id
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @ToString.Include
    @EqualsAndHashCode.Include
    private String title;

    @Column(nullable = false)
    @ToString.Include
    @EqualsAndHashCode.Include
    private String content;

    @Column(name = "user_id")
    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();
}
