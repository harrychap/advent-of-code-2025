package tasks

import utils.readInput

object Task02 : Task {

    override fun partA(): Long = parseInput()
        .flatMap { range ->
            range.filter { id ->
                val string = id.toString()
                if (string.length % 2 != 0) false

                val firstHalf = string.substring(0, string.length / 2)
                val secondHalf = string.substring(string.length / 2)

                firstHalf == secondHalf
            }
        }.sum()


    override fun partB(): Long = parseInput()
        .flatMap { range ->
            range.filter { id ->
                id.toString() matches """^(.+?)\1+$""".toRegex()
            }
        }.sum()

    private fun parseInput() =
        readInput("task02.txt").split(",").map { it ->
            val parts = it.split("-")
            parts.first().toLong()..parts.last().toLong()
        }
}