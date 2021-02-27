package polynom.polynomClass

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*


class Tests {

    @Test
    fun equalize() {
        assertEquals(
                listOf(Polynom(0.0, 3.0, 4.0, -8.0), Polynom(5.0, 2.0, 1.0, 7.0)),
                Polynom(3.0, 4.0, -8.0).equalize(Polynom(5.0, 2.0, 1.0, 7.0))
        )
        assertEquals(
                listOf(Polynom(4.0, 3.0, 4.0, -8.0), Polynom(5.0, 2.0, 1.0, 7.0)),
                Polynom(4.0, 3.0, 4.0, -8.0).equalize(Polynom(5.0, 2.0, 1.0, 7.0))
        )
        assertEquals(
                listOf(Polynom(4.0, 3.0, 4.0, -8.0), Polynom(0.0, 2.0, 1.0, 7.0)),
                Polynom(4.0, 3.0, 4.0, -8.0).equalize(Polynom(2.0, 1.0, 7.0))
        )
        assertEquals(
                listOf(Polynom(0.0, 1.0, 3.0, 2.0), Polynom(1.0, -2.0, -1.0, 4.0)),
                Polynom(1.0, 3.0, 2.0).equalize(Polynom(1.0, -2.0, -1.0, 4.0))
        )
        assertEquals(
                listOf(Polynom(1.0, -2.0, -1.0, 4.0), Polynom(0.0, 1.0, 3.0, 2.0)),
                Polynom(1.0, -2.0, -1.0, 4.0).equalize(Polynom(1.0, 3.0, 2.0))
        )
    }


    @Test
    fun getValue() {
        assertEquals(42.0, Polynom(1.0, 3.0, 2.0).getValue(5.0), 1e-10)
        assertEquals(10.0, Polynom(1.0, 2.0, 2.0).getValue(2.0), 1e-10)
        assertEquals(34.0, Polynom(1.0, -1.0, 6.0, -2.0).getValue(3.0), 1e-10)
    }


    @Test
    fun plus() {
        assertEquals(Polynom(1.0, -1.0, 2.0, 6.0), Polynom(1.0, -2.0, -1.0, 4.0).plus(Polynom(1.0, 3.0, 2.0)))
        assertEquals(Polynom(1.0, -1.0, 2.0, 6.0), Polynom(1.0, 3.0, 2.0).plus(Polynom(1.0, -2.0, -1.0, 4.0)))
    }


    @Test
    fun minus() {
        assertEquals(Polynom(1.0, -3.0, -4.0, 2.0), Polynom(1.0, -2.0, -1.0, 4.0).minus(Polynom(1.0, 3.0, 2.0)))

    }

    @Test
    fun times() {
        assertEquals(Polynom(1.0, 1.0, -5.0, -3.0, 10.0, 8.0), Polynom(1.0, -2.0, -1.0, 4.0).times(Polynom(1.0, 3.0, 2.0)))
        assertEquals(Polynom(1.0, 1.0, -5.0, -3.0, 10.0, 8.0), Polynom(1.0, 3.0, 2.0).times(Polynom(1.0, -2.0, -1.0, 4.0)))
    }

    @Test
    fun div() {
        assertEquals(Polynom(1.0, -5.0), Polynom(1.0, -2.0, -1.0, 4.0).div(Polynom(1.0, 3.0, 2.0)))

    }

    @Test
    fun rem() {
        assertEquals(Polynom(12.0, 14.0), Polynom(1.0, -2.0, -1.0, 4.0).rem(Polynom(1.0, 3.0, 2.0)))

    }

    @Test
    fun equals() {
        assertTrue(Polynom(1.0, 2.0, 3.0).equals(Polynom(1.0, 2.0, 3.0)))
        assertTrue(Polynom(0.0, 2.0, 3.0).equals(Polynom(2.0, 3.0)))
        assertFalse(Polynom(2.0, 0.0, 3.0).equals(Polynom(2.0, 3.0)))
        assertFalse(Polynom(-2.0, 3.0).equals(Polynom(2.0, 3.0)))
    }


    @Test
    fun output() {
        assertEquals("2.0x^2+3.0x-8.0", Polynom(2.0, 3.0, -8.0).output())
    }

}