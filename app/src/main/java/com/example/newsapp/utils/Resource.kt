package com.example.newsapp.utils

/**Resource-use to wrap around network resources **/
/**sealed class acts as abstract class but here we can define the classes that can inherite it**/
 sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>( data : T):Resource<T>(data)
    class Error<T>(message: String):Resource<T>()
    class Loading<T>: Resource<T>()
}