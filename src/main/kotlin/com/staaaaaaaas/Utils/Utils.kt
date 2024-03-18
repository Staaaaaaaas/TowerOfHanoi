package com.staaaaaaaas.Utils

import com.staaaaaaaas.Config.Config.numberOfTowers
import com.staaaaaaaas.Errors.UserInputError
import com.staaaaaaaas.Tower.Tower

fun getUserMove(towers: List<Tower>) {
    println("From where to where do you want to move the disk?" +
            "\nExample: 1 2\n" +
            "The above example moves a disk from the tower with number 1 to the tower with number 2.")
    while (true) {
        try {
            (readlnOrNull() ?: "").split(" ").mapNotNull { it.toIntOrNull() }.also { params ->
                if(params.size != 2) throw UserInputError("You have to enter exactly two numbers.")
                if(params.any {it !in 1..numberOfTowers})
                    throw UserInputError("There are no towers with such numbers.")
                towers[params[1] - 1].takeFrom(towers[params[0] - 1])
            }
            break
        }
        catch (err: UserInputError) {
            System.err.println(err.message)
        }
    }

}