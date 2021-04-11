package exercises.external.specification.functionalWithComposition

import psbp.external.specification.program.Functional

import psbp.external.specification.program.Composition

trait FunctionalWithComposition[>-->[- _, + _]]
  extends Functional[>-->]
  with Composition[>-->]

import exercises.external.specification.functional.{
  hello
  , comma
  , blank
  , world
  , bang
  , nwln
  , emptyStringProducer
  , stringConsumer
}

def helloWorld[>-->[- _, + _]: FunctionalWithComposition]: String >--> String =
  hello >--> comma >--> blank >--> world >--> bang >--> nwln

def mainHelloWorld[>-->[- _, + _]: FunctionalWithComposition]: Unit >--> Unit =
  emptyStringProducer >--> helloWorld >--> stringConsumer

