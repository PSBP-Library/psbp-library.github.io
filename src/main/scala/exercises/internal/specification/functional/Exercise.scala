package psbp.exercises.internal.specification.functional

import scala.language.postfixOps

import psbp.external.specification.program.Functional

import psbp.exercises.internal.specification.function

def genericProgramOfType_Z2Z[Z, >-->[- _, + _]: Functional]: Z >--> Z =

  val functional: Functional[>-->] = 
    summon[Functional[>-->]]
  import functional.toProgram

  toProgram(function.genericFunctionOfType_Z2Z)