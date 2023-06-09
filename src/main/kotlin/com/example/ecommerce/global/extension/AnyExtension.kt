package com.example.ecommerce.global.extension

object AnyExtension {

    val Any?.isNull: Boolean get() = this == null

    val Any?.isNotNull: Boolean get() = this != null
}