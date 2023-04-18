package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealServiceTest;

import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;
import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(DATAJPA)
public class DataJpaMealServiceTest extends MealServiceTest {

    @Test
    public void getWithUser() {
        User testUser = service.getWithUser(MEAL1_ID, USER_ID).getUser();
        USER_MATCHER.assertMatch(testUser, user);
    }
}
