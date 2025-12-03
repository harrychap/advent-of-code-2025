package tasks

import utils.readInput

object Task03 : Task {

    override fun partA(): Int = parseInput()
        .sumOf { bank ->
            var maxJolt = 0
            bank.forEachIndexed { index, value ->
               bank.forEachIndexed { otherIndex, otherValue ->
                   if (index != otherIndex && index < otherIndex) {
                       val jolt = "$value$otherValue".toInt()
                       if (maxJolt < jolt) maxJolt = jolt
                   }
               }
            }
            maxJolt
        }



    override fun partB(): Long {
        return 0
    }

    private fun parseInput() =
        readInput("task03.txt").split("\n").map { string -> string.map { it.digitToIntOrNull() } }
}