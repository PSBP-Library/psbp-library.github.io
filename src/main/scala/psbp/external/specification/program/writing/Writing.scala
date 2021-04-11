package psbp.external.specification.program.writing

import psbp.external.specification.types.&&

trait Writable[W]:

  // declared

  val nothing: W

  val append: (W && W) => W

  // defined extensions
  
  extension [Z, Y, X] (w1: W) 
    def +(w2: => W): W =
      append(w1, w2)

trait Writing[W: Writable, >-->[- _, + _]]:

  // declared

  private[psbp] def `w>-->u`: W >--> Unit
  
  // defined

  def write: W >--> Unit =
    `w>-->u`

