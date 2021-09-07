package com.smi.test.global.enumeration

sealed class Optional<out T> {
    class Some<out T>(val element: T) : Optional<T>()
    object None : Optional<Nothing>()
}