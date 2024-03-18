package com.staaaaaaaas.Tower

import com.staaaaaaaas.Config.Config.numberOfDisks
import com.staaaaaaaas.Errors.UserInputError

class Tower(val index: Int) {
    var stack = mutableListOf<Int>()
    fun takeFrom(other: Tower) {
        if(other.stack.isEmpty()) throw UserInputError("Illegal move: Tower ${other.index} is empty.")
        if(stack.isNotEmpty() && other.stack.last() > stack.last())
            throw UserInputError(
                "Illegal move: Top disk from tower $index is larger than the top disk from tower ${other.index}.")
        stack.add(other.stack.removeLast());
    }

    fun fill() {
        stack = (numberOfDisks downTo 1).toMutableList()
    }

    fun solved() = stack.size == numberOfDisks && stack.zip(stack.drop(1)).all { (a, b) -> a > b }

}