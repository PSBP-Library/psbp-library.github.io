package examples.specification.program

import psbp.specification.program.{ &&, Program }

import psbp.specification.program.{ `z>-->(z&&z)`, `(z&&y)>-->z` , `(z&&y&&x)>-->(y&&x)` }

def `construct using &&&`[>-->[- _, + _]: Program, Z, Y, X] (`z>-->y`: Z >--> Y, `z>-->x`: => Z >--> X): Z >--> (Y && X) =
  `z>-->(z&&z)` >--> (`z>-->y` &&& `z>-->x`)

def constructUsingLet[>-->[- _, + _]: Program, Z, Y, X] (`z>-->y`: Z >--> Y, `z>-->x`: => Z >--> X): Z >--> (Y && X) =
 
  val program: Program[>-->] = summon[Program[>-->]]
  import program.Let

  Let {
    `z>-->y`
  } In {
    Let {
      `(z&&y)>-->z` >--> `z>-->x`
    } In {
      `(z&&y&&x)>-->(y&&x)`
    }
  }

  