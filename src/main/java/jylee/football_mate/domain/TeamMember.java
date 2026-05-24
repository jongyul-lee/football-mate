package jylee.football_mate.domain;

import jakarta.persistence.*;
import jylee.football_mate.domain.enums.Position;
import jylee.football_mate.domain.enums.TeamRole;
import java.time.LocalDate;

@Entity
@Table(name = "team_members")
public class TeamMember {

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
    private LocalDate joined_at;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
