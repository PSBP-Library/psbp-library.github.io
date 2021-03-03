package examples.specification.program
  
import psbp.specification.program.Program  

import psbp.specification.functional.identity

import examples.specification.functional.{ isNotNegative, negate }

def negateIfNegative[>-->[- _, + _]: Program]: BigInt >--> BigInt =

  val program: Program[>-->] = summon[Program[>-->]]
  import program.If

  If(isNotNegative) {
    identity
  } Else {
    negate
  }