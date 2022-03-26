package com.dicoding.githubusersappapi.view

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.dicoding.githubusersappapi.R
import org.junit.Before
import org.junit.Test

class MainActivityTest{

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun assertSearchView() {
        onView(withId(R.id.search)).perform(click())
    }
}