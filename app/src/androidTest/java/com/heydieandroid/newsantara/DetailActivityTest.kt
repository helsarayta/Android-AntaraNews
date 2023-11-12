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
class DetailActivityTest {

    @get:Rule
    var detailActivityScenarioRule = ActivityScenarioRule(DetailActivity::class.java)

    @Test
    fun openDetailActivity() {
        onView(ViewMatchers.withId(R.id.sv_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun openDetailActivityAndScrollTest() {
        onView(ViewMatchers.withId(R.id.sv_detail)).perform(ViewActions.swipeUp())
    }

    @Test
    fun detailActivityAndClickLink() {
        onView(ViewMatchers.withId(R.id.sv_detail)).perform(ViewActions.swipeUp())
        onView(ViewMatchers.withId(R.id.detail_link)).perform(ViewActions.click())
    }
}
