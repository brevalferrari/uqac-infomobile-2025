package com.ferhatozcelik.jetpackcomposetemplate

import com.ferhatozcelik.jetpackcomposetemplate.util.format
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDateTime
import java.time.Period

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FormattingTest {
    @Test
    fun `LocalDateTime formatter with zeros`() {
        assertEquals("8h00 (5/8)", format(LocalDateTime.of(2025, 8, 5, 8, 0)))
    }

    @Test
    fun `LocalDateTime formatter with one zero`() {
        assertEquals("8h05 (5/8)", format(LocalDateTime.of(2025, 8, 5, 8, 5)))
    }

    @Test
    fun `LocalDateTime formatter without zeros`() {
        assertEquals("8h15 (5/8)", format(LocalDateTime.of(2025, 8, 5, 8, 15)))
    }

    @Test
    fun `LocalDateTime formatter with two hour digits`() {
        assertEquals("18h15 (5/8)", format(LocalDateTime.of(2025, 8, 5, 18, 15)))
    }

    @Test
    fun `LocalDateTime formatter at midnight (freaky)`() {
        assertEquals("0h00 (5/8)", format(LocalDateTime.of(2025, 8, 5, 0, 0)))
    }

    @Test
    fun `LocalDateTime formatter with two date digits per date digit date dates (digits)`() {
        assertEquals("0h00 (28/12)", format(LocalDateTime.of(2025, 12, 28, 0, 0)))
    }

    @Test
    fun `Period formatter one year`() {
        assertEquals("1 an", format(Period.ofYears(1)))
    }

    @Test
    fun `Period formatter one month`() {
        assertEquals("1 mois", format(Period.ofMonths(1)))
    }

    @Test
    fun `Period formatter one day`() {
        assertEquals("1 jour", format(Period.ofDays(1)))
    }

    @Test
    fun `Period formatter one year and one month`() {
        assertEquals("1 an et 1 mois", format(Period.of(1, 1, 0)))
    }

    @Test
    fun `Period formatter one year, one month and one day`() {
        assertEquals("1 an, 1 mois et 1 jour", format(Period.of(1, 1, 1)))
    }

    @Test
    fun `Period formatter one month and one day`() {
        assertEquals("1 mois et 1 jour", format(Period.of(0, 1, 1)))
    }

    @Test
    fun `Period formatter one year and one day`() {
        assertEquals("1 an et 1 jour", format(Period.of(1, 0, 1)))
    }

    @Test
    fun `Period formatter all plural`() {
        assertEquals("2 ans, 2 mois et 2 jours", format(Period.of(2, 2, 2)))
    }
}
