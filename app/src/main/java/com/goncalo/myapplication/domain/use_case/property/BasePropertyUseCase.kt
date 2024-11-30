package com.goncalo.myapplication.domain.use_case.property

abstract class BasePropertyUseCase<P,R,T> {

    suspend operator fun invoke(successParams: P? = null): T {
        return try {
            val timeStart = System.currentTimeMillis()
            val content = getContent()
            val timeEnd = System.currentTimeMillis()

            handleSuccess(content, timeEnd.minus(timeStart), successParams)
        } catch (e: Exception) {
            handleException(e)
        }
    }

    abstract suspend fun getContent(): R

    abstract fun handleSuccess(content: R, requestDuration: Long, successParams: P?): T

    abstract fun handleException(obj: Exception): T

}