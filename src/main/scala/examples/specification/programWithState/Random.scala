package examples.specification.programWithState

import scala.language.postfixOps

import psbp.specification.programWithState.ProgramWithState

import examples.specification.program.negateIfNegative

type Seed = Long

def random[Z, >-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithState[Seed, >-->]]: Z >--> BigInt =

  val programWithSeedState: ProgramWithState[Seed, >-->] = summon[ProgramWithState[Seed, >-->]]
  import programWithSeedState.readStateModifiedWith
  
  object function {
    val seedModifier: Seed => Seed = 
      seed =>
        (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
  
    val seed2randomBigInt: Seed => BigInt = 
      seed =>
        BigInt((seed >>> 16).toInt)
        
    val moduloSomeLong: BigInt => BigInt =
      n =>
        n % 9876543210L     
  }
 
  val seedModifier: Seed >--> Seed =
    function.seedModifier asProgram

  val seed2randomBigInt =
    function.seed2randomBigInt asProgram

  val moduloSomeLong =
    function.moduloSomeLong asProgram

  val readModifiedSeed = readStateModifiedWith(seedModifier)   

  readModifiedSeed >--> seed2randomBigInt >--> negateIfNegative >--> moduloSomeLong 
