package dev.shreyansh.dice.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import dev.shreyansh.dice.utils.getOrAwaitValueTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import dev.shreyansh.dice.R
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DiceViewModelTest{

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DiceViewModel

    @Before
    fun setup(){
        viewModel = DiceViewModel(ApplicationProvider.getApplicationContext())
    }


    //subjectUnderTest_actionOrInput_resultState
    @Test
    fun updateLoginLiveData_onLogin_returnsTrue(){
        //GIVEN

        //WHEN
        viewModel.updateLogin()
        //THEN
        var updateLoginLiveData = viewModel.loginComplete.getOrAwaitValueTest()
        assertThat(updateLoginLiveData).isTrue()
    }

    @Test
    fun loginCompleteLiveData_onLoginComplete_returnsFalse(){
        //GIVEN

        //WHEN
        viewModel.onLoginComplete()
        //THEN
        var loginCompleteLiveData = viewModel.loginComplete.getOrAwaitValueTest()
        assertThat(loginCompleteLiveData).isFalse()
    }

    @Test
    fun loginCancelLiveData_onLoginCancel_returnsFalse(){
        //GIVEN

        //WHEN
        viewModel.onLoginCancel()
        //THEN
        var loginCancelLiveData = viewModel.loginComplete.getOrAwaitValueTest()
        assertThat(loginCancelLiveData).isFalse()
    }

    @Test
    fun eventGameStartLiveData_onGameStart_returnsTrue(){
        //GIVEN

        //WHEN
        viewModel.onGameStart()
        //THEN
        var eventGameStartLiveData = viewModel.eventGameStart.getOrAwaitValueTest()
        assertThat(eventGameStartLiveData).isTrue()
    }

    @Test
    fun eventGameStartLiveData_onGameStartComplete_returnsFalse(){
        //GIVEN

        //WHEN
        viewModel.onGameStartComplete()
        //THEN
        var eventGameStartLiveData = viewModel.eventGameStart.getOrAwaitValueTest()
        assertThat(eventGameStartLiveData).isFalse()
    }

    @Test
    fun onPrefsReset_onResetData_returnsDefaultData(){
        //GIVEN

        //WHEN
        viewModel.resetData()
        //THEN
        assertThat(viewModel.dice1.getOrAwaitValueTest()).isEqualTo(R.drawable.empty_dice)
        assertThat(viewModel.dice2.getOrAwaitValueTest()).isEqualTo(R.drawable.empty_dice)
        assertThat(viewModel.dice3.getOrAwaitValueTest()).isEqualTo(R.drawable.empty_dice)
        assertThat(viewModel.dice4.getOrAwaitValueTest()).isEqualTo(R.drawable.empty_dice)
        assertThat(viewModel.result.getOrAwaitValueTest()).isEqualTo("")

    }


}