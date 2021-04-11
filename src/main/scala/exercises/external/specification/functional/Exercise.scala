package exercises.external.specification.functional

import scala.language.postfixOps

// import psbp.external.specification.program.Identity

import psbp.external.specification.program.Functional

import exercises.external.specification.function

// def `z>-->z`[Z, >-->[- _, + _]: Functional]: Z >--> Z =
//   function.`z==>z` asProgram

// def `z>-s->z`[Z, >-->[- _, + _]: Functional]: Z >--> Z =
//   function.`z=s=>z` asProgram

// given identityFromFunctional[>-->[- _, + _]: Functional]: Identity[>-->] with
//   override def `z>-->z`[Z]: Z >--> Z =
//     function.`z==>z` asProgram

def hello[>-->[- _, + _]: Functional]: String >--> String =
  function.hello asProgram

def comma[>-->[- _, + _]: Functional]: String >--> String =
  function.comma asProgram
  
def blank[>-->[- _, + _]: Functional]: String >--> String =
  function.blank asProgram

def world[>-->[- _, + _]: Functional]: String >--> String =
  function.world asProgram

def bang[>-->[- _, + _]: Functional]: String >--> String =
  function.bang asProgram

def nwln[>-->[- _, + _]: Functional]: String >--> String =
  function.nwln asProgram

def emptyStringProducer[>-->[- _, + _]: Functional]: Unit >--> String =
  function.emptyStringProducer asProgram

def stringConsumer[>-->[- _, + _]: Functional]: String >--> Unit =
  function.stringConsumer asProgram 


