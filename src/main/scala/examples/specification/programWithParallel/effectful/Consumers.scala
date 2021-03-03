package examples.specification.programWithParallel.effectful

import scala.language.postfixOps

import psbp.specification.types.&&

import psbp.specification.program.Program 

import akka.actor.typed.{ ActorSystem, Behavior }

import akka.actor.typed.scaladsl.{ ActorContext, Behaviors }
  
import Behaviors.{ receive, stopped }
  
import ch.qos.logback.classic.{Level, Logger, LoggerContext}
import Level.{ INFO, ERROR}
  
import org.slf4j.LoggerFactory
  
val loggerContext:  LoggerContext = LoggerFactory.getILoggerFactory().asInstanceOf[LoggerContext];
val logger: Logger = loggerContext.getLogger("examples.specification.programWithParallel.effectful");
  
def log[Z](actorContext: ActorContext[Z])(message: String): Unit = {
  logger.setLevel(INFO);
  actorContext.log.info(message)
  logger.setLevel(ERROR);  
}
  
def parallelFibonacciConsumer[>-->[- _, + _]: Program]: (BigInt && BigInt) >--> Unit =
    
  object Consumer {

    case class Consume(i: BigInt, j: BigInt)

    def apply(): Behavior[Consume] = receive { (context, message) =>
      message match {
        case Consume(i, j) =>
          log(context)(s"applying parallel fibonacci to argument $i yields result $j")
          stopped
      }
    }

  }

  val consumer = ActorSystem(Consumer(), "consumer")  
   
  import Consumer.Consume

  {
    (`i&&j`: BigInt && BigInt) =>
      val i = `i&&j`._1
      val j = `i&&j`._2
      consumer ! Consume(i, j)
  } asProgram  


