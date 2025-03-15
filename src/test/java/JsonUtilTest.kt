import io.github.yanwensiyi.kt.util.array
import io.github.yanwensiyi.kt.util.eq
import io.github.yanwensiyi.kt.util.json

fun main() {
    val json = json {
        1 eq 2
        3 eq "4"
        5 eq array(6, "7", json {
            8 eq -8
        })
        "9" eq json {
            10 eq 11
        }
    }
    println(json)
}