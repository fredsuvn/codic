package org.codician.common.base

interface NamingConverter {

    val tokenizer: Tokenizer

    val joiner: Joiner

    fun convert(chars: CharSequence): String = if (chars.isEmpty()) chars.toString() else joiner.join(tokenizer.tokenize(chars))

    interface Tokenizer {

        fun tokenize(chars: CharSequence): TokenStream
    }

    interface TokenStream {

        fun next(): Int

        fun wordCount(): Int

        fun charCount(): Int

        companion object {

            const val WORD_START = 0xff_ff_00_01.toInt()

            const val WORD_END = 0xff_ff_00_02.toInt()

            const val STREAM_END = 0xff_ff_ff_ff.toInt()
        }
    }

    interface Joiner {

        fun join(tokenStream: TokenStream): String
    }
}

abstract class CamelCase : NamingConverter {

    abstract fun doFirstChar(c: Char): Char

    override val tokenizer: NamingConverter.Tokenizer
        get() = CamelCaseTokenizer

    override val joiner: NamingConverter.Joiner
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun convert(chars: CharSequence): String {
        return super.convert(chars)
    }

    object CamelCaseTokenizer : NamingConverter.Tokenizer {
        override fun tokenize(chars: CharSequence): NamingConverter.TokenStream =
                CamelCaseTokenStream(chars)
    }

    class CamelCaseTokenStream(private val chars: CharSequence) : NamingConverter.TokenStream {

        private var pointer = -1
        private var prevLower = false

        private var nextToken: Int? = null

        override fun next(): Int {
            if (nextToken != null) {
                val ret = nextToken as Int
                nextToken = null
                return ret
            }

            if (pointer > 0) {
                if (pointer < chars.length) {
                    val c = chars[pointer]

                }
                return NamingConverter.TokenStream.STREAM_END
            }

            if (pointer < 0) {
                return NamingConverter.TokenStream.WORD_START
            }

            //case pointer == 0
            prevLower = chars[0].isLowerCase()
            pointer = 1
            return chars[0].toInt()
        }

        override fun wordCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun charCount(): Int = chars.length
    }
}