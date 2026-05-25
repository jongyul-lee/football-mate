package jylee.football_mate.domain;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
@Getter
public class Team {

    protected Team(){}

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
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_user_id")
    private User createdUser;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<TeamMember> teamMemberList = new ArrayList<TeamMember>();

    //== 연관관계 메서드 ==//
    public void setCreatedUser(User user){
        this.createdUser = user;
        user.getCreatedTeams().add(this);
    }

    //== 생성 메서드 ==//
    public static Team createTeam(String name, String description, String region, User createdUser){
        Team team = new Team();
        team.name = name;
        team.description = description;
        team.region = region;
        team.createdAt = LocalDate.now();
        team.setCreatedUser(createdUser);
        return team;
    }

    //== 비즈니스 로직 ==//
    public void addTeamMember(TeamMember teamMember){
        this.teamMemberList.add(teamMember);
        teamMember.setTeam(this);
    }
}
