package exercises.external.specification.functional

import scala.language.postfixOps

import psbp.external.specification.program.Functional

import exercises.external.specification.function

def genericProgramOfType_Z2Z[Z, >-->[- _, + _]: Functional]: Z >--> Z =
  function.genericFunctionOfType_Z2Z asProgram

