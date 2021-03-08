package examples

// val factorial: BigInt => BigInt =
//   i =>
//     if(i == 0) {
//       1
//     } else { 
//       val j = factorial(i - 1)
//       i * j
//     }

import examples.specification.function.{ isZero, one, subtractOne, multiply }

val factorial: BigInt => BigInt =
  i =>
    if(isZero(i)) {
      one(i)
    } else { 
      val j = factorial(subtractOne(i))
      multiply(i, j)
    }

@main def mainFactorial(args: String*): Unit =
  println("Please type an integer")
  val i: BigInt = BigInt(scala.io.StdIn.readInt)
  println(s"applying factorial to the integer argument ${i} yields result ${factorial(i)}")
  