package io.github.yanwensiyi.kt.util

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

context(JSONObject)
inline infix fun String.eq(value: Any?): JSONObject = put(this@eq, JSONObject.wrap(value))

context(JSONObject)
inline infix fun Int.eq(value: Any?): JSONObject = put(this@eq.toString(), JSONObject.wrap(value))

context(JSONObject)
inline infix fun Long.eq(value: Any?): JSONObject = put(this@eq.toString(), JSONObject.wrap(value))

context(JSONObject)
inline fun array(vararg args: Any?) = JSONArray().apply {
    args.forEach(::put)
}

inline fun json(block: JSONObject.() -> Unit) = JSONObject().apply(block)
inline fun jsonArray(vararg args: Any?) = JSONArray().apply {
    args.forEach(::put)
}

inline fun String.parseJson(): JSONObject = JSONObject(this)

inline fun Any?.toJson(): Any? = JSONObject.wrap(this)

@Throws(JSONException::class)
inline fun Any?.toJsonObject() = JSONObject(this)

@Throws(JSONException::class)
inline fun Any.toJsonArray() = JSONArray(this)