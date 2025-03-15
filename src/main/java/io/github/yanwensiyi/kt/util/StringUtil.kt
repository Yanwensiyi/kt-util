package io.github.yanwensiyi.kt.util

context(StringBuilder)
inline operator fun Any?.unaryPlus(): StringBuilder = append(this@unaryPlus)