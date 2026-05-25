package jylee.football_mate.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class UserTest {

    @Test
    void 유저생성(){
        //given
        String email = "test@gmail.com";
        String password = "1234";
        String name = "jylee";
        String phone = "010-1111-6789";

        //when
        User user = User.createUser(email, password, name, phone);

        //then
        Assertions.assertThat(user.getEmail()).isEqualTo(email);
        Assertions.assertThat(user.getPassword()).isEqualTo(password);
        Assertions.assertThat(user.getName()).isEqualTo(name);
        Assertions.assertThat(user.getPhone()).isEqualTo(phone);
        Assertions.assertThat(user.getCreatedAt()).isEqualTo(LocalDate.now());
    }

    @Test
    void 프로필_이미지_수정(){
        //given
        String url = "http://test.com/test.png";
        User user = User.createUser("test@gmail.com", "1234", "jylee", "010-1111-6789");


        //when
        user.updateProfileImage(url);

        //then
        Assertions.assertThat(user.getProfileImageUrl()).isEqualTo(url);
    }
}
