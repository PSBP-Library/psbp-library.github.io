package psbp.external.specification.program.writing

import scala.language.postfixOps

import psbp.external.specification.types.&&

trait Writable[W]:

  // declared

  def nothing: W

  def append: (W && W) => W

  // defined extensions
  
  extension [Z, Y, X] (w1: W) 
    def +(w2: => W): W =
      append(w1, w2)

trait ToWritable[Y, W: Writable, >-->[- _, + _]]:

  def converter: Y >--> W


trait Writing[W: Writable, >-->[- _, + _]]:

  def write: W >--> Unit
