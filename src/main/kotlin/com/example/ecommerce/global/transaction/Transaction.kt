package com.example.ecommerce.global.transaction

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class Transaction {

    @Transactional
    fun <R> write(block: () -> R): Result<R> = runCatching { block() }

    @Transactional(readOnly = true)
    fun <R> readOnly(block: () -> R): Result<R> = runCatching { block() }
}