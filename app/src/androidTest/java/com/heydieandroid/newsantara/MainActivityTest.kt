package com.heydieandroid.newsantara

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var mainActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun testOpenMainActivity() {
        onView(ViewMatchers.withId(R.id.rv_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testMainActivityToDetailActivity() {
        onView(ViewMatchers.withId(R.id.rv_list)).perform(ViewActions.click())
    }

    @Test
    fun mainActivityToDetailActivityAndScroll() {
        onView(ViewMatchers.withId(R.id.rv_list)).perform(ViewActions.swipeUp()).perform(ViewActions.swipeDown())
    }

    @Test
    fun testMainActivityToAboutActivity() {
        onView(ViewMatchers.withId(R.id.action_about)).perform(ViewActions.click())
    }


}
