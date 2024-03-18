package com.staaaaaaaas.Utils

import com.staaaaaaaas.Config.Config
import com.staaaaaaaas.Config.Config.NUMBER_OF_TOWERS
import com.staaaaaaaas.Errors.UserInputError
import com.staaaaaaaas.Tower.Tower
import kotlin.system.exitProcess

fun getUserMove(towers: List<Tower>) {
    println(
        "From where to where do you want to move the disk?" +
            "\nExample: 1 2\n" +
            "The above example moves a disk from the tower with number 1 to the tower with number 2."
    )
    while (true) {
        try {
            val input = (readlnOrNull() ?: "")
            if (input in Config.EXIT_COMMANDS) exitProcess(0)
            input.split(" ").mapNotNull { it.toIntOrNull() }.also { params ->
                if (params.size != 2) throw UserInputError("You have to enter exactly two numbers.")
                if (params.any { it !in 1..NUMBER_OF_TOWERS }) {
                    throw UserInputError("There are no towers with such numbers.")
                }
                towers[params[1] - 1].takeFrom(towers[params[0] - 1])
            }
            break
        } catch (err: UserInputError) {
            System.err.println(err.message)
        }
    }
}
