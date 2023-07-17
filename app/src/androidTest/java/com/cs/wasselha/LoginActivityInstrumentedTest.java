package com.cs.wasselha;

import android.content.Context;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.cs.wasselha.Login.LoginActivity;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginActivityInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.cs.wasselha", appContext.getPackageName());
    }
    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void performLogin_success() {
        // Enter valid login credentials
        Espresso.onView(ViewMatchers.withId(R.id.emailInLoginPage))
                .perform(ViewActions.typeText("aws@g.com"));
        Espresso.onView(ViewMatchers.withId(R.id.passwordInLoginPage))
                .perform(ViewActions.typeText("1234"));

        // Click on the login button
        Espresso.onView(ViewMatchers.withId(R.id.loginBtnInLoginPage))
                .perform(ViewActions.click());

        // Assert that the login is successful
        // Here you can add your own assertions based on your application's logic
        // For example, you can check if a new activity is launched or a success message is shown.
        // You can use Espresso's onView and ViewMatchers to perform assertions.

        // Example: Assert that a welcome message is displayed
        Espresso.onView(ViewMatchers.withId(R.id.errorMessage))
                .check(ViewAssertions.matches(ViewMatchers.hasErrorText("")));
    }

    @Test
    public void performLogin_invalidCredentials() {
        // Enter invalid login credentials
        Espresso.onView(ViewMatchers.withId(R.id.emailInLoginPage))
                .perform(ViewActions.typeText("invalid_username"));
        Espresso.onView(ViewMatchers.withId(R.id.passwordInLoginPage))
                .perform(ViewActions.typeText("invalid_password"));

        // Click on the login button
        Espresso.onView(ViewMatchers.withId(R.id.loginBtnInLoginPage))
                .perform(ViewActions.click());

        // Assert that the login fails
        // Here you can add your own assertions based on your application's logic
        // For example, you can check if an error message is shown.

        // Example: Assert that an error message is displayed
        Espresso.onView(ViewMatchers.withId(R.id.errorMessage))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}