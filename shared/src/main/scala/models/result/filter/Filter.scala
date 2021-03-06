package models.result.filter

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

object Filter {
  implicit val jsonEncoder: Encoder[Filter] = deriveEncoder
  implicit val jsonDecoder: Decoder[Filter] = deriveDecoder
}

case class Filter(
    k: String = "?",
    o: FilterOp = Equal,
    v: Seq[String] = Nil
)
