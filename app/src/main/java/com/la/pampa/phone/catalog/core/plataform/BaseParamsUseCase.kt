package com.la.pampa.phone.catalog.core.plataform

import com.la.pampa.phone.catalog.core.ResultOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class BaseParamsUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): ResultOf<Type>

    open operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        onResult: (ResultOf<Type>) -> Unit = {}
    ) {
        val backgroundJob = scope.async { run(params) }
        scope.launch { onResult(backgroundJob.await()) }
    }

}