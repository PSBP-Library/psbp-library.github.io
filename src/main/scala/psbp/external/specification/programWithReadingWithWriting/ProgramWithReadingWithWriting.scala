package psbp.external.specification.programWithReadingWithWriting

import psbp.external.specification.types.&&

import psbp.external.specification.program.Program

import psbp.external.specification.program.reading.{
  Readable
  , FromReadable
  , Reading
}

import psbp.external.specification.program.writing.{
  Writable
  , ToWritable
  , Writing
}

trait ProgramWithReadingWithWriting[
  R: Readable
  , W: Writable
  , >-->[- _, + _]
] 
  extends Program[>-->]
  with Reading[R, >-->] 
  with Writing[W, >-->]

/*

def mainFactorial[
  >-->[- _, + _]: [>-->[- _, + _]] =>> Producer[BigInt, >-->]
                : [>-->[- _, + _]] =>> ProgramWithReadingWithWriting[StdIn[BigInt], StdOut, >-->]
                : [>-->[- _, + _]] =>> Consumer[BigInt && BigInt, >-->]
]: Unit >--> Unit =
  
  val producer: Producer[BigInt, >-->] = summon[Producer[BigInt, >-->]]
  import producer.`u>-->z`

  val consumer: Consumer[BigInt && BigInt, >-->] = summon[Consumer[BigInt && BigInt, >-->]]
  import consumer.`y>-->u`

  toMainProgram(
    produce = `u>-->z`
    , program = factorial
    , consume = `y>-->u`
  )

*/

// def toMain[
//   Z, Y
//   , R: Readable
//   , W: Writable
//   , >-->[- _, + _]: [>-->[- _, + _]] =>> Producer[R, >-->]
//                   : [>-->[- _, + _]] =>> FromReadable[R, Z, >-->]
//                   : [>-->[- _, + _]] =>> ToWritable[Z && Y, W, >-->]
// ](`z>-->y`: Z >--> Y): Unit >--> Unit =

//   val programWithReadingWithWriting: ProgramWithReadingWithWriting[R, W, >-->] =
//     summon[ProgramWithReadingWithWriting[R, W, >-->]]
//   import programWithReadingWithWriting.{
//     Let
//     , `u>-->r`
//     , `w>-->u`
//   }  

//   val fromReadable: FromReadable[R, Z, >-->] =
//     summon[FromReadable[R, Z, >-->]]
//   import fromReadable.`r>-->z`

//   val toWritable: ToWritable[Z && Y, W, >-->] =
//     summon[ToWritable[Z && Y, W, >-->]]
//   import toWritable.{
//     `y>-->w` => `(z&&y)=>w`
//   }

//   `u>-->r` >--> `r>-->z`
//     >--> {
//       Let { 
//         `z>-->y`
//       } In { 
//         `(z&&y)=>w` >--> `w>-->u` 
//       }
//     }


