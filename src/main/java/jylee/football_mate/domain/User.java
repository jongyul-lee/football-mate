package jylee.football_mate.domain;

import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "profile_image")
    private String profile_image_url;

    @Column(name = "created_at")
    LocalDate created_at;

    @OneToMany(mappedBy = "created_user")
    private List<Team> createdTeams = new ArrayList<Team>();

    @OneToMany(mappedBy = "user")
    private List<TeamMember> teamMemberList = new ArrayList<TeamMember>();
}