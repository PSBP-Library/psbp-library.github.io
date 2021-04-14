package psbp.external.specification.programWithReadingWithWriting

import psbp.external.specification.types.&&

import psbp.external.specification.program.{
  Program
  , toMain => programToMain
}

import psbp.external.specification.programWithReading.ProgramWithReading

import psbp.external.specification.program.reading.{
  FromReadable
  , Reading
}

import psbp.external.specification.program.writing.{
  Writable
  , ToWritable
  , Writing
}

trait ProgramWithReadingWithWriting[
  R
  , W: Writable
  , >-->[- _, + _]
] 
  extends Program[>-->]
  with Reading[R, >-->] 
  with Writing[W, >-->]

def toMain[
  Z, Y
  , R
  , W: Writable
  , >-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithReadingWithWriting[R, W, >-->]
                  : [>-->[- _, + _]] =>> FromReadable[R, Z, >-->]
                  : [>-->[- _, + _]] =>> ToWritable[Z && Y, W, >-->]
](program: => Z >--> Y): Unit >--> Unit =

  val programWithReadingWithWriting: ProgramWithReadingWithWriting[R, W, >-->] =
    summon[ProgramWithReadingWithWriting[R, W, >-->]]
  import programWithReadingWithWriting.{
    Let
    , read
    , write
  }  

  val fromReadable: FromReadable[R, Z, >-->] =
    summon[FromReadable[R, Z, >-->]]
  import fromReadable.{
    converter => convertFromReadable
  }

  val toWritable: ToWritable[Z && Y, W, >-->] =
    summon[ToWritable[Z && Y, W, >-->]]
  import toWritable.{ 
    converter => convertToWritable
  }
   
  {
    
  // println(">>> in toMain")

    read >--> convertFromReadable
    >--> {
      Let { 
        program
      } In { 
        convertToWritable >--> write 
      }
    }

  }


  // programToMain(
  //   producer = read >--> convertFromReadable
  //   , program = program
  //   , consumer = convertToWritable >--> write
  // )

