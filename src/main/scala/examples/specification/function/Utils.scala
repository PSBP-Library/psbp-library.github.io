package examples.specification.function

import psbp.external.specification.types.{
  &&
  , ||
}  

import psbp.external.specification.function.foldSum

import psbp.external.specification.function.foldSeq

import psbp.external.implementation.list.List

import psbp.external.implementation.rec.list.{
  RecursiveList
  , emptyRecursiveList
  , consRecursiveList
} 

val isZero: BigInt => Boolean =
  n =>
   n == 0
  
def one[Z]: Z => BigInt =
  _ =>
    1
  
val subtractOne: BigInt => BigInt =
  n =>
    n - 1
    
val multiply: BigInt && BigInt => BigInt =
  (n, m) =>
    n * m

def zero[Z]: Z => BigInt =
  _ =>
    0

val isOne: BigInt => Boolean =
  n =>
   n == 1
   
val subtractTwo: BigInt => BigInt =
  n =>
    n - 2   

val add: BigInt && BigInt => BigInt =
  (n, m) =>
    n + m

val isNegative: BigInt => Boolean =
  n =>
   n < 0

val negate: BigInt => BigInt =
  n =>
    -n
        
def constantTrue[Z]: Z => Boolean = { _ =>
  true
} 

def and: (Boolean && Boolean) => Boolean = { (b, a) =>
  b && a
}   

val isPositive: BigInt => Boolean =
  n =>
   n > 0  
    
val areAllTrueFolder: (Unit || (Boolean && Boolean)) => Boolean =
  foldSum(constantTrue, and)

def seqToRecursiveList[Z]: Seq[Z] => RecursiveList[Z] = 
  foldSeq(emptyRecursiveList, consRecursiveList) 

import  psbp.external.specification.types.||

import ||.{Left, Right}

import psbp.internal.implementation.structure.recursive.{
  mkNone
}

def fromSeqUnfolder[Z]: Seq[Z] => List[Z, Seq[Z]] =
  case z +: zs => Right((z, zs))
  case _ => Left(mkNone)
  
def emptySeq[Z]: Unit => Seq[Z] = 
  _ =>
    Seq()

def consSeq[Z]: Z && Seq[Z] => Seq[Z] =
  case (z, zs) => 
    z +: zs

// def toSeqFolder[Z]: (Unit || (Z && Seq[Z])) => Seq[Z] = 
//   foldSum(emptySeq, consSeq) 

def toSeqFolder[Z]: List[Z, Seq[Z]] => Seq[Z] = 
  foldSum(emptySeq, consSeq) 
  
// introduction 

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