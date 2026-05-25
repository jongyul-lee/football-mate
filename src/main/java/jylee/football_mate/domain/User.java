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

    protected User(){}

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
    LocalDate createdAt;

    @OneToMany(mappedBy = "createdUser")
    private List<Team> createdTeams = new ArrayList<Team>();

    @OneToMany(mappedBy = "user")
    private List<TeamMember> teamMemberList = new ArrayList<TeamMember>();

    //== 생성 메서드 ==//
    public static User createUser(String email, String password, String name, String phone){
        User user = new User();
        user.email = email;
        user.password = password;
        user.name = name;
        user.phone = phone;
        user.createdAt = LocalDate.now();
        return user;
    }

    //== 비즈니스 로직 ==//
    public void updateProfileImage(String url){
        this.profile_image_url = url;
    }

}
