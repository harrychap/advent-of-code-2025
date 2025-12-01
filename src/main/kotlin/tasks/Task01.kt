package tasks

import utils.readInput
import java.lang.Math.floorDiv
import kotlin.math.abs

object Task01 : Task {

    override fun partA(): Int {
        var position = 50
        var total = 0
        parseInput().forEach { num ->
            position = (position + num) % 100
            if (position == 0) total++
        }
        return total
    }

    override fun partB(): Int {
        var position = 50
        var total = 0
        parseInput().forEach { num ->
            total = total + rotate(position, position + num)
            position = (position + num) % 100

        }

        return total
    }

    private fun rotate(counter: Int, rotation: Int): Int = when {
        counter < rotation -> abs(floorDiv(rotation, 100) - floorDiv(counter, 100))
        counter > rotation -> rotate(-counter, -rotation)
        else -> 0
    }

    private fun parseInput() =
        readInput("task01.txt").split("\n").map { it.drop(1).toInt() * if (it[0] == 'R') -1 else 1 }
}