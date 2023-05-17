package com.example.gamecompose.data.common

sealed class ResourceNetwork<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
   class Success<T>(data: T) : ResourceNetwork<T>(data = data)
   class Loading<T>(data: T? = null) : ResourceNetwork<T>(data = data)
   class Error<T>(data: T? = null, error: Throwable? = null) : ResourceNetwork<T>(data = data,
                                                                                  error = error)
}
