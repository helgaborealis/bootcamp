package co.alty.data.api.exception

class DataNetworkException(
  val mes: String,
  val originalException: Throwable
) : DataException(mes, originalException)