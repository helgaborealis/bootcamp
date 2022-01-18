package co.alty.data.api.exception

abstract class DataException(mes: String, exception: Throwable?) : RuntimeException(mes, exception)