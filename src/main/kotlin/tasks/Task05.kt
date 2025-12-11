package tasks

import utils.readInput

object Task05 : Task {

    override fun partA(): Int = ids().count { id ->
        ranges().any { range ->
            id in range
        }
    }

    override fun partB(): Long {
        val usedIds = mutableListOf<LongRange>()
        ranges().sortedBy { it.start }.forEach { range ->
            if (usedIds.isEmpty() || usedIds.last().last < range.start) {
                usedIds.add(range.start .. range.last)
            } else {
                val last = usedIds.removeAt(usedIds.lastIndex)

                usedIds.add(last.first .. maxOf(last.last, range.last))
            }
        }
        return usedIds.sumOf { it.last - it.first + 1 }
    }

    private fun parseInput() = readInput("task05.txt").split("\n\n")

    private fun ranges() = parseInput().first().split("\n").map { it.split("-") }
        .map { range -> range.first().toLong()..range.last().toLong() }

    private fun ids() = parseInput().last().split("\n").map { it.toLong() }
}