package com.dirask.repository;

import com.dirask.model.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void should_create_new_user() {
        UserEntity user = new UserEntity("Tom", 25);
        userRepository.save(user);

        UserEntity userByName = userRepository.findOneByName("Tom");
        assertThat(userByName).isNotNull();

        // User{id=2, name='Tom', age=25}
        System.out.println(userByName.toString());
    }

    @Test
    public void should_find_all_users() {
        Iterable<UserEntity> users = userRepository.findAll();
        assertThat(users).hasSize(1);
    }

    @Test
    public void should_delete_user() {
        UserEntity userByName = userRepository.findOneByName("Kate");
        assertThat(userByName).isNotNull();

        userRepository.delete(userByName);

        Iterable<UserEntity> users = userRepository.findAll();
        assertThat(users).hasSize(0);
    }
}
