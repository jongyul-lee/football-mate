package jylee.football_mate.domain;

import jylee.football_mate.domain.enums.Position;
import jylee.football_mate.domain.enums.TeamRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class TeamTest {

    @Test
    void 팀생성(){
        //given
        String email = "test@gmail.com";
        String password = "1234";
        String name = "jylee";
        String phone = "010-1111-6789";

        String teamName = "성은FC";
        String description = "초보팀입니다.";
        String region = "인천";

        //when
        User user = User.createUser(email, password, name, phone);
        Team team = Team.createTeam(teamName, description, region, user);


        //then
        Assertions.assertThat(team.getName()).isEqualTo(teamName);
        Assertions.assertThat(team.getDescription()).isEqualTo(description);
        Assertions.assertThat(team.getRegion()).isEqualTo(region);
        Assertions.assertThat(team.getCreatedUser()).isEqualTo(user);
        Assertions.assertThat(team.getCreatedAt()).isEqualTo(LocalDate.now());
    }

    @Test
    void 팀멤버추가(){
        //given
        String email = "test@gmail.com";
        String password = "1234";
        String name = "jylee";
        String phone = "010-1111-6789";

        String teamName = "성은FC";
        String description = "초보팀입니다.";
        String region = "인천";

        //when
        User user = User.createUser(email, password, name, phone);
        Team team = Team.createTeam(teamName, description, region, user);
        TeamMember teamMember = TeamMember.createTeamMember(TeamRole.OWNER, Position.MF, 10, user, team);

        //then

        //team에 teamMember가 잘 들어갔는지 확인
        Assertions.assertThat(team.getTeamMemberList()).contains(teamMember);
        Assertions.assertThat(team.getTeamMemberList()).hasSize(1);

        // user에 teamMember가 잘 들어갔는지 확인
        Assertions.assertThat(user.getTeamMemberList()).contains(teamMember);
        Assertions.assertThat(user.getTeamMemberList()).hasSize(1);

        // teamMember가 어떤 user/team을 참조하는지
        Assertions.assertThat(teamMember.getUser()).isEqualTo(user);
        Assertions.assertThat(teamMember.getTeam()).isEqualTo(team);

        // 기타 다른값 검증
        Assertions.assertThat(teamMember.getRole()).isEqualTo(TeamRole.OWNER);
        Assertions.assertThat(teamMember.getPosition()).isEqualTo(Position.MF);
        Assertions.assertThat(teamMember.getUniformNumber()).isEqualTo(10);
    }

    @Test
    void 팀생성시_생멤버와_팀이_양방향으로_연결(){
        //given
        String email = "test@gmail.com";
        String password = "1234";
        String name = "jylee";
        String phone = "010-1111-6789";

        String teamName = "성은FC";
        String description = "초보팀입니다.";
        String region = "인천";

        //when
        User user = User.createUser(email, password, name, phone);
        Team team = Team.createTeam(teamName, description, region, user);

        Assertions.assertThat(user.getCreatedTeams()).contains(team);
        Assertions.assertThat(team.getCreatedUser()).isEqualTo(user);
    }
}
