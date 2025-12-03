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


    override fun partB(): Long = parseInputIndex().sumOf { bank ->
        var usedIndex = -1
        bank.windowed(bank.size - 11, 1).map { window ->
            val highest = window.filterNot { usedIndex >= it.first }.sortedByDescending { it.second }.first()
            usedIndex = highest.first
            highest.second
        }.joinToString(separator = "").toLong()
    }

    private fun parseInput() =
        readInput("task03.txt").split("\n").map { string -> string.map { it.digitToIntOrNull() } }

    private fun parseInputIndex() =
        readInput("task03.txt").split("\n")
            .map { string -> string.mapIndexed { index, num -> index to num.digitToIntOrNull() } }
}