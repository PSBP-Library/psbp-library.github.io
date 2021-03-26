package examples.specification.programWithParallel.effectful

import scala.language.postfixOps

import akka.actor.typed.{ ActorSystem, Behavior }

import akka.actor.typed.scaladsl.{ ActorContext, Behaviors }
  
import Behaviors.{ receive, stopped }
  
import ch.qos.logback.classic.{ Level, Logger, LoggerContext }
import Level.{ INFO, ERROR }
  
import org.slf4j.LoggerFactory

import psbp.external.specifcation.types.&&

import psbp.external.specifcation.program.Program 

val packageName = "examples.specification.programWithParallel.effectful"
  
val loggerContext: LoggerContext = 
  LoggerFactory.getILoggerFactory().asInstanceOf[LoggerContext]
val logger: Logger = 
  loggerContext.getLogger(packageName)
  
def logInfo[Z](actorContext: ActorContext[Z])(message: String): Unit =
  logger.setLevel(INFO)
  actorContext.log.info(message)
  logger.setLevel(ERROR)
  
def parallelFibonacciConsumer[
  >-->[- _, + _]: Program
  ]: (BigInt && BigInt) >--> Unit =
    
  object Consumer {

    case class Consume(i: BigInt, j: BigInt)

    def apply(): Behavior[Consume] = 
      receive { (context, message) =>
        message match {
          case Consume(i, j) =>
            val info = logInfo(context)
            info(s"applying parallel fibonacci to argument $i yields result $j")
            stopped
        }
      }

  }

  val consumer = ActorSystem(Consumer(), "consumer")  
   
  import Consumer.Consume

  val sendConsumeToConsumer: (BigInt && BigInt) => Unit =
    (i, j) =>
      consumer ! Consume(i, j)

  sendConsumeToConsumer asProgram 



