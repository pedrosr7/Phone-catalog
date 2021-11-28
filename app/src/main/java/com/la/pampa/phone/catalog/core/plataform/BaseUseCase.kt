package com.la.pampa.phone.catalog.core.plataform

import com.la.pampa.phone.catalog.core.ResultOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class BaseUseCase<out Type> where Type : Any {

    abstract suspend fun run(): ResultOf<Type>

    open operator fun invoke(
        scope: CoroutineScope,
        onResult: (ResultOf<Type>) -> Unit = {}
    ) {
        val backgroundJob = scope.async { run() }
        scope.launch { onResult(backgroundJob.await()) }
    }

}