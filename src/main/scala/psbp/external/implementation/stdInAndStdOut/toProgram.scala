// package psbp.external.implementation.stdInAndStdOut

// import psbp.external.implementation.stdIn.StdIn

// import psbp.external.implementation.stdOut.StdOut

// import psbp.external.specification.programWithReading.Producer

// import psbp.external.specification.programWithReading.Consumer

//  def toMain[
//   Z, Y
//   , >-->[- _, + _]: Program
// ](program: Z >--> Y): Unit >--> Unit =

//   val program_ : Program[>-->] = 
//     summon[Program[>-->]]
//   import program_.Let  

//   produce
//     >--> {
//       Let { 
//         program
//       } In { 
//         consume
//       }
//     } 