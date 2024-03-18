package com.staaaaaaaas.Visuals

import com.staaaaaaaas.Config.Config.block
import com.staaaaaaaas.Config.Config.horizontal
import com.staaaaaaaas.Config.Config.intersection
import com.staaaaaaaas.Config.Config.newline
import com.staaaaaaaas.Config.Config.numberOfDisks
import com.staaaaaaaas.Config.Config.spacing
import com.staaaaaaaas.Config.Config.vertical
import com.staaaaaaaas.Tower.Tower

fun emptyLine(middle: String) = " ".repeat(numberOfDisks) + middle + " ".repeat(numberOfDisks)

fun diskLine(diskSize: Int) =
    " ".repeat(numberOfDisks - diskSize) +
    block.repeat(2 * diskSize + 1) + " ".repeat(numberOfDisks - diskSize)

fun base() = horizontal.repeat(numberOfDisks) + intersection + horizontal.repeat(numberOfDisks)

fun drawTower(tower: Tower) =
    emptyLine(vertical) + newline +
    if(tower.stack.isEmpty())
        List(numberOfDisks){ emptyLine(vertical) }.joinToString(newline)
    else {
        if(tower.stack.size == numberOfDisks ) {
            tower.stack.joinToString(newline) {
                diskLine(it)
            }.reversed()
        }
        else {
            List(numberOfDisks - tower.stack.size){ emptyLine(vertical) }.joinToString(newline) +
            newline + tower.stack.joinToString(newline) {
                diskLine(it)
            }.reversed()
        }

    } + newline + base() + newline + emptyLine(tower.index.toString())

fun joinTowers(left: String, right: String) =
    left.lines().zip(right.lines())
    .joinToString(newline) { it.first + " ".repeat(spacing) + it.second }

fun drawTowers(towers: List<Tower>) = println(towers.drop(1).map{ drawTower(it) }.fold(
    drawTower(towers.first())) { acc, el -> joinTowers(acc, el) })
