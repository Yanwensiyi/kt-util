package io.github.yanwensiyi.kt.util

import org.json.JSONArray
import org.json.JSONObject

/**
 * For internal use, avoid using this class directly.
 * If context context parameter released in the future,
 * this class can be removed.
 */
class JsonScope {
    val obj = JSONObject()
    override fun toString() = obj.toString()
    infix fun String.to(value: Any?): JSONObject = obj.put(
        this, when (value) {
            null -> JSONObject.NULL
            else -> value
        }
    )

    infix fun Int.to(value: Any?): JSONObject = obj.put(
        Int.toString(), when (value) {
            null -> JSONObject.NULL
            else -> value
        }
    )

    infix fun Long.to(value: Any?): JSONObject = obj.put(
        Long.toString(), when (value) {
            null -> JSONObject.NULL
            else -> value
        }
    )

    fun list(vararg args: Any?) = JSONArray().apply {
        args.forEach {
            put(it ?: JSONObject.NULL)
        }
    }

    fun json(block: JsonScope.() -> Unit) = JsonScope().apply(block).obj
}

inline fun json(block: JsonScope.() -> Unit) = JsonScope().apply(block).toString()
inline fun jsonObject(block: JsonScope.() -> Unit) = JsonScope().apply(block).obj
inline fun jsonArray(vararg args: Any?) = args.toJsonArray()
fun String.parseJson(): JSONObject = JSONObject(this)

fun Map<*, *>.toJsonObject(): JSONObject = JSONObject().apply {
    this@toJsonObject.forEach { k, v ->
        when (v) {
            is Map<*, *> -> put(k.toString(), v.toJsonObject())
            is List<*>   -> put(k.toString(), v.toJsonArray())
            is Array<*>  -> put(k.toString(), v.toJsonArray())
            else         -> put(k.toString(), v)
        }
    }
}

fun List<*>.toJsonArray(): JSONArray = JSONArray().apply {
    this@toJsonArray.forEach { v ->
        when (v) {
            is Map<*, *> -> put(v.toJsonObject())
            is List<*>   -> put(v.toJsonArray())
            is Array<*>  -> put(v.toJsonArray())
            else         -> put(v)
        }
    }
}

fun Array<*>.toJsonArray(): JSONArray = JSONArray().apply {
    this@toJsonArray.forEach { v ->
        when (v) {
            is Map<*, *> -> put(v.toJsonObject())
            is List<*>   -> put(v.toJsonArray())
            is Array<*>  -> put(v.toJsonArray())
            else         -> put(v)
        }
    }
}

fun Map<*, *>.toJson() = toJsonObject().toString()
fun List<*>.toJson() = toJsonArray().toString()
fun Array<*>.toJson() = toJsonArray().toString()
fun Any?.toJson(): Any? = JSONObject.wrap(this)