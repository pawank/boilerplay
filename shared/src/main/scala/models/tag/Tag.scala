package models.tag

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}

object Tag {
  implicit val jsonEncoder: Encoder[Tag] = deriveEncoder
  implicit val jsonDecoder: Decoder[Tag] = deriveDecoder

  def fromString(s: String) = s.split(",").map(_.split("=").toList match {
    case h :: t :: Nil => Tag(h, t)
    case x => throw new IllegalStateException("Parse error: " + x.toString)
  }).toSeq

  def toString(seq: Seq[Tag]) = seq.map(t => t.k + "=" + t.v).mkString(",")

  def fromMap(m: Map[_, _]) = m.toSeq.map(e => Tag(e._1.toString, e._2.toString))

  def fromJavaMap(m: java.util.HashMap[_, _]) = {
    import scala.collection.JavaConverters._
    fromMap(m.asScala.toMap)
  }

  def toJavaMap(seq: Seq[Tag]) = {
    val map = new java.util.HashMap[String, String]()
    seq.foreach(tag => map.put(tag.k, tag.v))
    map
  }
}

case class Tag(k: String, v: String)
