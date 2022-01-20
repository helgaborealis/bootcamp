package co.alty.data.api.exception

class DataHttpException constructor(
  val mes: String,
  val originalException: Throwable,
  val code: Int
) : DataException(mes, originalException)