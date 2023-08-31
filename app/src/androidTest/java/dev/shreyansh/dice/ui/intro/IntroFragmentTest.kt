package dev.shreyansh.dice.ui.intro

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.filters.SmallTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import dev.shreyansh.dice.R
import dev.shreyansh.dice.launchFragmentInHiltContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
@MediumTest
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class IntroFragmentTest{

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        hiltRule.inject()
    }


    @Test
    fun testFrag(){
        launchFragmentInHiltContainer<IntroFragment>(Bundle(),R.style.Theme_AppCompat)
        Espresso.onView(withId(R.id.appname_textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("DICE")))


    }
}