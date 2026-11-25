package cp.campus.todolist.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "usuarios",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_usuarios_email",
                columnNames = "email"
        )
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false,length = 150)
    private String nombre;

    @Column(nullable = false,length = 250)
    private String email;


    @Column(nullable = false,length = 250)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 250)
    private ROL rol;


    @Column(nullable = false)
    private boolean vip;

    public Usuarios(String nombre, String email, String passwordHash) {
        this.nombre = nombre;
        this.email = email;
        this.passwordHash = passwordHash;
        this.rol=ROL.CLIENTE;
        this.vip= false;
    }


}
