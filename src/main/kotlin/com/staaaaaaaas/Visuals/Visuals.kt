package com.staaaaaaaas.Visuals

import com.staaaaaaaas.Config.Config.BLOCK
import com.staaaaaaaas.Config.Config.HORIZONTAL
import com.staaaaaaaas.Config.Config.INTERSECTION
import com.staaaaaaaas.Config.Config.NUMBER_OF_DISKS
import com.staaaaaaaas.Config.Config.SPACING
import com.staaaaaaaas.Config.Config.VERTICAL
import com.staaaaaaaas.Config.Config.newline
import com.staaaaaaaas.Tower.Tower

fun emptyLine(middle: String) = " ".repeat(NUMBER_OF_DISKS) + middle + " ".repeat(NUMBER_OF_DISKS)

fun diskLine(diskSize: Int) =
    " ".repeat(NUMBER_OF_DISKS - diskSize) +
        BLOCK.repeat(2 * diskSize + 1) + " ".repeat(NUMBER_OF_DISKS - diskSize)

fun base() = HORIZONTAL.repeat(NUMBER_OF_DISKS) + INTERSECTION + HORIZONTAL.repeat(NUMBER_OF_DISKS)

fun drawTower(tower: Tower) =
    emptyLine(VERTICAL) + newline +
        if (tower.stack.isEmpty()) {
            List(NUMBER_OF_DISKS) { emptyLine(VERTICAL) }.joinToString(newline)
        } else {
            if (tower.stack.size == NUMBER_OF_DISKS) {
                tower.stack.joinToString(newline) {
                    diskLine(it)
                }.reversed()
            } else {
                List(NUMBER_OF_DISKS - tower.stack.size) { emptyLine(VERTICAL) }.joinToString(newline) +
                    newline + tower.stack.joinToString(newline) {
                    diskLine(it)
                }.reversed()
            }
        } + newline + base() + newline + emptyLine(tower.index.toString())

fun joinTowers(left: String, right: String) =
    left.lines().zip(right.lines())
        .joinToString(newline) { it.first + " ".repeat(SPACING) + it.second }

fun drawTowers(towers: List<Tower>) = println(
    towers.drop(1).map { drawTower(it) }.fold(
        drawTower(towers.first())
    ) { acc, el -> joinTowers(acc, el) }
)
