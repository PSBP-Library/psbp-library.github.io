package examples.specification.programWithState

import scala.language.postfixOps

import psbp.external.specification.functional.identity

import psbp.external.specification.programWithState.ProgramWithState

import examples.specification.functional.{ 
  isNegative
  , negate 
  }

type Seed = Long

def random[
  >-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithState[Seed, >-->]
  , Z]: Z >--> BigInt =

  val programWithSeedState: ProgramWithState[Seed, >-->] =
    summon[ProgramWithState[Seed, >-->]]
  import programWithSeedState.{
    If
    , readStateModifiedWith
    }

  object function {

    val seedModifier: Seed => Seed = 
      seed =>
        (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
  
    val seed2randomBigInt: Seed => BigInt = 
      seed =>
        BigInt((seed >>> 16).toInt) 
        
    val moduloSomeBigInt: BigInt => BigInt =
      n =>
        n % BigInt("9876543210")

  }    

  val readModifiedSeed: Z >--> Seed = 
    readStateModifiedWith(function.seedModifier asProgram)  

  val seed2randomBigInt: Seed >--> BigInt =
    function.seed2randomBigInt asProgram

  val negateIfNegative: BigInt >--> BigInt =
    If(isNegative) {
      negate
    } Else {
      identity
    } 
  
  val moduloSomeBigInt: BigInt >--> BigInt =
    function.moduloSomeBigInt asProgram 

  readModifiedSeed >--> 
    seed2randomBigInt >--> 
      negateIfNegative >--> 
        moduloSomeBigInt
