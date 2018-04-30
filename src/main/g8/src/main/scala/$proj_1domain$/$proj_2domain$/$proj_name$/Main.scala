package $proj_1domain$.$proj_2domain$.$proj_name$

import com.typesafe.scalalogging.LazyLogging
import scopt.OptionParser

import scala.util.{Failure, Success}

case class CommandLineArgs(file: Path, kwargs: Map[String,String] = Map())

object Main extends LazyLogging {
  val defaultConfigFileName = Paths.get("application.conf")
  val progVersion = "0.1"
  val progName = "$proj_name$"
  val entryText =
    """
      |
      |NOTE:
      |copy logback.xml locally, modify it with desired logger levels and specify
      |-Dlogback.configurationFile=/path/to/customised/logback.xml. Keep in mind
      |that "Logback-classic can scan for changes in its configuration file and
      |automatically reconfigure itself when the configuration file changes".
      |So, you even don't need to relaunch your process to change logging levels
      | -- https://goo.gl/HMXCqY
      |
    """.stripMargin

  def run(config: CommandLineArgs): Unit = {
    logger.info(s"running ${progName} version ${progVersion}")
    val conf = pureconfig.loadConfig[Configuration](config.file)

    conf match {
      case Right(c) =>
        logger.debug("loading default configuracion")
      case Left(fails) => logger.error(s"${fails.toString}")
    }

    logger.info(s"ending ${progName} version ${progVersion}")
  }

  def main(args: Array[String]) {
    // parser.parse returns Option[C]
    parser.parse(args, CommandLineArgs(file = defaultConfigFileName)) match {
      case Some(config) =>
        run(config)
      case None =>
    }
  }

  val parser:OptionParser[CommandLineArgs] = new OptionParser[CommandLineArgs](progName) {
    head(progName, progVersion)

    opt[String]('f', "file").required()
      .valueName("<config-file>")
      .action( (x, c) => c.copy(file = Paths.get(x)) )
      .text("file contains the configuration")

    opt[Map[String,String]]("kwargs")
      .valueName("k1=v1,k2=v2...")
      .action( (x, c) => c.copy(kwargs = x) )
      .text("other arguments")

    note(entryText)
  }
}
