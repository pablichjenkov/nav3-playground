package com.macaosoftware.nav3playground.common.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.compositionLocalOf
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * Local for storing results in a [ResultStore]
 */
object LocalResultStore {
    private val LocalResultStore: ProvidableCompositionLocal<ResultStore?> =
        compositionLocalOf { null }

    /**
     * The current [ResultStore]
     */
    val current: ResultStore
        @Composable
        get() = LocalResultStore.current ?: error("No ResultStore has been provided")

    /**
     * Provides a [ResultStore] to the composition
     */
    infix fun provides(
        store: ResultStore
    ): ProvidedValue<ResultStore?> {
        return LocalResultStore.provides(value = store)
    }
}

/**
 * A store for passing results between multiple sets of screens.
 *
 * It provides solutions for both event and state based results, where the correct result may
 * be based on the use case.
 */
class ResultStore {
    /**
     * Map from the result key to a channel of results.
     */
    val channelMap: MutableMap<String, Channel<Any?>> = mutableMapOf()

    /**
     * Map from the result key to a mutable state of the result.
     */
    val resultMap: MutableMap<String, Any> = mutableMapOf()

    /**
     * Provides a flow for the given resultKey.
     */
    inline fun <reified T> getResultFlow(resultKey: String = T::class.toString()) =
        channelMap[resultKey]?.receiveAsFlow()

    /**
     * Sends a result into the channel associated with the given resultKey.
     */
    inline fun <reified T> sendResult(resultKey: String = T::class.toString(), result: T) {
        if (!channelMap.contains(resultKey)) {
            channelMap.put(resultKey, Channel(capacity = BUFFERED, onBufferOverflow = BufferOverflow.SUSPEND))
        }
        channelMap[resultKey]?.trySend(result)
    }

    /**
     * Retrieves the current result of the given resultKey.
     */
    inline fun <reified T : Any> getResult(resultKey: String = T::class.toString()) =
        resultMap[resultKey] as? T

    /**
     * Sets the result for the given resultKey.
     */
    inline fun <reified T : Any> setResult(resultKey: String = T::class.toString(), result: T) {
        resultMap.put(resultKey, result)
    }

    /**
     * Removes all results associated with the given key from the store.
     */
    inline fun <reified T> removeResult(resultKey: String = T::class.toString()) {
        resultMap.remove(resultKey)
        channelMap.remove(resultKey)
    }
}