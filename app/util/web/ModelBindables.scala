package util.web

import models.template.Theme
import models.user.Role
import play.api.mvc.{PathBindable, QueryStringBindable}

object ModelBindables {
  private[this] def roleExtractor(v: Either[String, String]) = v match {
    case Right(s) => Right(Role.withName(s))
    case Left(x) => throw new IllegalStateException(x)
  }
  implicit def rolePathBindable(implicit stringBinder: PathBindable[String]): PathBindable[Role] = new PathBindable[Role] {
    override def bind(key: String, value: String) = roleExtractor(stringBinder.bind(key, value))
    override def unbind(key: String, x: Role) = x.entryName
  }
  implicit def roleQueryStringBindable(implicit stringBinder: QueryStringBindable[String]): QueryStringBindable[Role] = new QueryStringBindable[Role] {
    override def bind(key: String, params: Map[String, Seq[String]]) = stringBinder.bind(key, params).map(roleExtractor)
    override def unbind(key: String, x: Role): String = x.entryName
  }

  private[this] def themeExtractor(v: Either[String, String]) = v match {
    case Right(s) => Right(Theme.withValue(s))
    case Left(x) => throw new IllegalStateException(x)
  }
  implicit def themePathBindable(implicit stringBinder: PathBindable[String]): PathBindable[Theme] = new PathBindable[Theme] {
    override def bind(key: String, value: String) = themeExtractor(stringBinder.bind(key, value))
    override def unbind(key: String, x: Theme) = x.value
  }
  implicit def themeQueryStringBindable(implicit stringBinder: QueryStringBindable[String]): QueryStringBindable[Theme] = new QueryStringBindable[Theme] {
    override def bind(key: String, params: Map[String, Seq[String]]) = stringBinder.bind(key, params).map(themeExtractor)
    override def unbind(key: String, x: Theme): String = x.value
  }

  /* Begin model bindables */
  /* End model bindables */
}
