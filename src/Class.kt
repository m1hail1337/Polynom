package polynom.polynomClass

import kotlin.math.pow

/**Вариант 1 -- целочисленный полином
 * Хранит полином вида 7x^4+3x^3-6x^2+x-8. Все коэффициенты -- вещественные числа.
 * Методы: сравнить два полинома на равенство, рассчитать значение при данном(целом) x,
 * сложить / вычесть / умножить / поделить два полинома, найти остаток от деления одного полинома на другой.
 */
class Polynom(vararg val coeffs: Double) {
    private val coefList = coeffs.toMutableList()

    fun equalize(other: Polynom): List<Polynom> { // Функция для уравнивания длины двух полиномов с помощью добавления в начало 0.0
        when {
            other.coeffs.size == this.coeffs.size -> return listOf(this, other)
            this.coeffs.size > other.coeffs.size -> for (i in 0 until this.coeffs.size - other.coeffs.size) other.coefList.add(
                0,
                0.0
            )
            else -> for (i in 0 until other.coeffs.size - this.coeffs.size) this.coefList.add(0, 0.0)
        }
        return listOf(Polynom(*this.coefList.toDoubleArray()), Polynom(*other.coefList.toDoubleArray()))
    }

    private fun divisionHelper(other: Polynom, flagDiv: Boolean): Polynom { // Функция для реализации деления и взятия остатка
        var diffSize = this.coeffs.size - other.coeffs.size
        val result = mutableListOf<Double>()
        var remains = this
        for (i in 0..diffSize) {
            val divisor = remains.coeffs[i] / other.coeffs[0]
            val helpList = other.coefList.map { it * divisor }.toMutableList()
            for (i in 0 until diffSize) helpList += 0.0
            remains -= Polynom(*helpList.toDoubleArray())
            result += divisor
            diffSize--
        }
        return if (flagDiv) Polynom(*result.toDoubleArray())
        else remains
    }

    /**
     * Расчёт значения при заданном x
     */
    fun getValue(x: Double): Double {
        var result = 0.0
        var maxDegree = coeffs.size - 1
        for (coef in coeffs) {
            result += coef * x.pow(maxDegree)
            maxDegree--
        }
        return result
    }


    /**
     * Сложение
     */
    operator fun plus(other: Polynom): Polynom {
        this.equalize(other)
        return Polynom(*this.coefList.mapIndexed
        { index, element -> element + other.coefList[index] }
            .toDoubleArray())
    }


    /**
     * Вычитание
     */
    operator fun minus(other: Polynom): Polynom {
        this.equalize(other)
        return Polynom(*this.coefList.mapIndexed
        { index, element -> element - other.coefList[index] }
            .toDoubleArray())
    }

    /**
     * Умножение
     */
    operator fun times(other: Polynom): Polynom {
        this.equalize(other)
        val parts = mutableListOf<Polynom>()
        var maxDegree = this.coeffs.size - 1
        for (i in this.coeffs) {
            val newCoeffs = other.coefList.map { it * i }.toMutableList()
            for (i in 0 until maxDegree) newCoeffs += 0.0
            parts += Polynom(*newCoeffs.toDoubleArray())
            maxDegree--
        }
        var result = Polynom()
        parts.forEach {
            result += it
        }
        return result
    }

    /**
     * Деление
     *
     * Про операции деления и взятия остатка см. статью Википедии
     * "Деление многочленов столбиком". Основные свойства:
     *
     * Если A / B = C и A % B = D, то A = B * C + D и степень D меньше степени B
     */
    operator fun div(other: Polynom): Polynom = divisionHelper(other, true)
    /**
     * Взятие остатка
     */
    operator fun rem(other: Polynom): Polynom {
        println(divisionHelper(other, false).coefList)
        return divisionHelper(other, false)
    }

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean =
        when {
            this === other -> true
            other is Polynom -> {
                val polynoms = this.equalize(other)
                polynoms[0].coefList.toDoubleArray() contentEquals polynoms[1].coefList.toDoubleArray()
            }
            else -> false
        }

    /**
     * Представление полинома в виде строки типа 5.0х^2+2.0x-7.0
     */

    fun output(): String {
        val p = this
        var degree = this.coeffs.size - 1
        return buildString {
            for (i in p.coeffs.indices) {
                if (coeffs[i] != 0.0) {
                    if (coeffs[i] > 0.0 && i > 0) append("+${coeffs[i]}")
                    else append("${coeffs[i]}")
                    when {
                        degree > 1 -> append("x^$degree")
                        degree == 1 -> append("x")
                    }
                }
                degree--
            }
        }
    }
}