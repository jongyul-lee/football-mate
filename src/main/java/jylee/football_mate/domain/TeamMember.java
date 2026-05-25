package jylee.football_mate.domain;

import jakarta.persistence.*;
import jylee.football_mate.domain.enums.Position;
import jylee.football_mate.domain.enums.TeamRole;
import java.time.LocalDate;

@Entity
@Table(name = "team_members")
public class TeamMember {

    protected TeamMember(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_member_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private TeamRole role;

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private Position position;

    @Column(name = "uniform_number")
    private Integer uniformNumber;

    @Column(name = "joined_at")
    private LocalDate joinedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //== 연관관계 메서드 ==//
    public void setTeam(Team team){
        this.team = team;

        if(!team.getTeamMemberList().contains(this)){
            team.getTeamMemberList().add(this);
        }
    }

    public void setUser(User user){
        this.user = user;

        if(!user.getTeamMemberList().contains(this)){
            user.getTeamMemberList().add(this);
        }
    }

    //== 생성 메서드 및 가입(?) 로직==//
    public static TeamMember createTeamMember(TeamRole role, Position position, Integer uniformNumber, User user, Team team){
        TeamMember teamMember = new TeamMember();
        teamMember.role = role;
        teamMember.position = position;
        teamMember.uniformNumber = uniformNumber;
        teamMember.joinedAt = LocalDate.now();

        teamMember.setUser(user);
        teamMember.setTeam(team);

        return teamMember;
    }

    //== 비즈니스 로직 ==//
    public void changeRole(TeamRole role){
        this.role = role;
    }

    public void changeUniformNumber(Integer number){
        this.uniformNumber = number;
    }

    public void changePosition(Position position){
        this.position = position;
    }
}
