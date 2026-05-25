package jylee.football_mate.domain;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
@Getter
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Column(name = "team_name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "region")
    private String region;

    @Column(name = "created_at")
    private Timestamp created_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_user_id")
    private User created_user;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<TeamMember> teamMemberList = new ArrayList<TeamMember>();

    //== 연관관계 메서드 ==//
    public void setCreatedUser(User user){
        this.created_user = user;
        user.getCreatedTeams().add(this);
    }
}
