package psbp.external.implementation.stdIn

import scala.language.postfixOps

import psbp.external.specification.program.Program

import psbp.external.specification.program.reading.Readable

import psbp.external.specification.program.reading.FromReadable

given givenArgumentFromStdInReadable[
  Z: [Z] =>> Readable[StdIn[Z]]
  , >-->[- _, + _]: Program
]: FromReadable[StdIn[Z], Z, >-->] with

  private type R = StdIn[Z]

  override def `r>-->z`: R >--> Z =

    object function {

      val `r=>z`: R => Z =
        case StdIn(i) =>
          i

    }

    function.`r=>z` asProgram




    
// import psbp.external.implementation.stdIn.givenStdInReadable

// given givenBigIntFromStdInBigInt[>-->[- _, + _]: Program]: FromReadable[StdIn[BigInt], BigInt, >-->] with

//   private type R = StdIn[BigInt]

//   private type Z = BigInt

//   override def `r>-->z`: R >--> Z =

//     object function {

//       val `r=>z`: R => Z =
//         case StdIn(i) =>
//           i

//     }

//     function.`r=>z` asProgram