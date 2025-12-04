package tasks

import utils.readInput

object Task04 : Task {

    override fun partA(): Int {
        val pointMap = parseInput()

        return pointMap.filter { it.value == '@' }.count { point ->
            val expandedPoints = pointMap.expand(point.key.first, point.key.second)
            expandedPoints.count { it == '@' } < 4
        }
    }

    override fun partB(): Int {
        var canRepeat = true
        var tempMap = parseInput()
        var count = 0

        while (canRepeat) {
            val newMap = tempMap.map { point ->
                if (point.value != '@') return@map point.key to point.value

                val expandedPoints = tempMap.expand(point.key.first, point.key.second)

                if (expandedPoints.count { it == '@' } < 4) {
                    ++count
                    point.key to '.'
                } else {
                    point.key to '@'
                }
            }.associateBy({ it.first }, { it.second })

            if (newMap == tempMap) {
                canRepeat = false
            } else {
                tempMap = newMap
            }
        }
        return count
    }

    private fun Map<Pair<Int, Int>, Char>.expand(x: Int, y: Int): List<Char?> = listOf(
        this[Pair(x + 1, y)],
        this[Pair(x - 1, y)],
        this[Pair(x, y + 1)],
        this[Pair(x, y - 1)],
        this[Pair(x - 1, y + 1)],
        this[Pair(x - 1, y - 1)],
        this[Pair(x + 1, y + 1)],
        this[Pair(x + 1, y - 1)]
    )

    private fun parseInput() =
        readInput("task04.txt").split("\n").flatMapIndexed { y, row ->
            row.mapIndexed { x, char ->
                Pair(x, y) to char
            }
        }.associateBy({ it.first }, { it.second })
}