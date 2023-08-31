package dev.shreyansh.dice.utils

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import dev.shreyansh.dice.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UtilityTest {

    @Test
    fun setImage() {
        val res1 = setImage(1)
        val res2 = setImage(3)
        val res3 = setImage(18)

        assertThat(res1).isEqualTo(R.drawable.dice_1)
        assertThat(res2).isEqualTo(R.drawable.dice_3)
        assertThat(res3).isEqualTo(R.drawable.dice_6)

    }
}