package org.codician.common

fun main(args: Array<String>) {
    println("""
        The problems of your past are your business.
        The problems of your future aren't my privilege.
    """.trimIndent())

    val i = 0xff_ff_00_92.toInt()
    println(i)
    val c = i.toChar()
    println(c)
    println(c.toInt())
}