package psbp.external.specification.program.writing

import scala.language.postfixOps

import psbp.external.specification.types.&&

trait Writable[W]:

  // declared

  private[psbp] def emptyW: W

  private[psbp] def appendW: (W && W) => W

  // defined

  def empty: W =
    emptyW
  
  // defined extensions

  extension [Z, Y, X] (w1: W) 
    def +(w2: W): W =
      appendW(w1, w2)

trait ToWritable[Y, W: Writable, >-->[- _, + _]]:

  // declare
  
  private[psbp] def `y>-->w`: Y >--> W

  // define

  private[psbp] def converter: Y >--> W =
    `y>-->w`


trait Writing[W: Writable, >-->[- _, + _]]:

  // declare
  
  private[psbp] def writeW: W >--> Unit

  // define

  def `w>-->u`: W >--> Unit =
    writeW

  def write: W >--> Unit =
    `w>-->u`

// import psbp.external.specification.programWithWriting.ProgramWithWriting

// def write[
//   Y: [Y] =>> ToWritable[Y, W, >-->]
//   , W: Writable
//   , >-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithWriting[W, >-->]
// ]: Y >--> Unit =

//   val toWritable: ToWritable[Y, W, >-->] = 
//     summon[ToWritable[Y, W, >-->]]
//   import toWritable.`y>-->w`

//   val writing: Writing[W, >-->] = 
//     summon[Writing[W, >-->]]
//   import writing.`w>-->u`

//   `y>-->w` >--> `w>-->u`