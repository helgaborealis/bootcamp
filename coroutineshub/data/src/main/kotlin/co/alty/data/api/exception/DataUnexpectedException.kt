package co.alty.data.api.exception

class DataUnexpectedException(
  val mes: String,
  val originalException: Throwable? = null
) : DataException(mes, originalException)