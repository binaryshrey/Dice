package dev.shreyansh.dice.ui.game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.shreyansh.dice.ui.utils.getOrAwaitValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GameViewModelTest {

    private lateinit var viewModel: GameViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUpViewModel() {
        viewModel = GameViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun test_roll_no_empty_result(){
        //GIVEN
        viewModel.roll()

        //WHEN
        val value = viewModel.result.getOrAwaitValue()

        //THEN
        assertEquals(value.length,14)
    }
}