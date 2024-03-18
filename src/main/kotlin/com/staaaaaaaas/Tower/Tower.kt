package com.staaaaaaaas.Tower

import com.staaaaaaaas.Config.Config.NUMBER_OF_DISKS
import com.staaaaaaaas.Errors.UserInputError

class Tower(val index: Int) {
    var stack = mutableListOf<Int>()
    fun takeFrom(other: Tower) {
        if (other.stack.isEmpty()) throw UserInputError("Illegal move: Tower ${other.index} is empty.")
        if (stack.isNotEmpty() && other.stack.last() > stack.last()) {
            throw UserInputError(
                "Illegal move: Top disk from tower $index is larger than the top disk from tower ${other.index}."
            )
        }
        stack.add(other.stack.removeLast())
    }

    fun fill() {
        stack = (NUMBER_OF_DISKS downTo 1).toMutableList()
    }

    fun solved() = stack.size == NUMBER_OF_DISKS && stack.zip(stack.drop(1)).all { (a, b) -> a > b }
}
