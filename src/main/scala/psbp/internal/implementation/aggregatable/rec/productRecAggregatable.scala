package psbp.internal.implementation.aggregatable.rec

import psbp.external.specifcation.types.&&

import psbp.external.specifcation.function.and

import psbp.internal.specification.lifting.{ Function0Lifting, Function1Lifting, Function1LiftingAtRight , Function1LiftingAtLeft, FunctionLifting }

import psbp.external.specifcation.types.Rec

import psbp.internal.specification.aggregatable.rec.{ RecReducerLifting, RecAggregatable, RecInitialTraverser, RecInitialReducer, RecFunctionLevelFusing }

// private[psbp] type Product[L[+ _, + _], R[+ _, + _]] = [Y, X] =>> L[Y, X] && R[Y, X]  

given productFunction1LiftingAtLeft[
  L[+ _, + _]: [L[+ _, + _]] =>> Function1LiftingAtLeft[L], 
  R[+ _, + _]: [R[+ _, + _]] =>> Function1LiftingAtLeft[R]]: Function1LiftingAtLeft[Product[L, R]] with

  private val leftFunction1LiftingAtLeft = summon[Function1LiftingAtLeft[L]]
  private val rightFunction1LiftingAtLeft = summon[Function1LiftingAtLeft[R]]

  import leftFunction1LiftingAtLeft.{ leftFunction1Lifting => leftLeftFunction1Lifting }
  import rightFunction1LiftingAtLeft.{ leftFunction1Lifting => rightLeftFunction1Lifting }

  private[psbp] def leftFunction1Lifting[X] : Function1Lifting[[Y] =>> Product[L, R][Y, X]] =
  new {
    override def lift1[Z, Y]
      : (Z => Y) => Product[L, R][Z, X] => Product[L, R][Y, X] = { `z=>y` =>
        and(
          leftLeftFunction1Lifting.lift1(`z=>y`), 
          rightLeftFunction1Lifting.lift1(`z=>y`)
          )
      }
  }

given productFunction1LiftingAtRight[
  L[+ _, + _]: [L[+ _, + _]] =>> Function1LiftingAtRight[L], 
  R[+ _, + _]: [R[+ _, + _]] =>> Function1LiftingAtRight[R]]: Function1LiftingAtRight[Product[L, R]] with
  
  private val leftFunction1LiftingAtRight = summon[Function1LiftingAtRight[L]]
  private val rightFunction1LiftingAtRight = summon[Function1LiftingAtRight[R]]
  
  import leftFunction1LiftingAtRight.{ rightFunction1Lifting => leftRightFunction1Lifting }
  import rightFunction1LiftingAtRight.{ rightFunction1Lifting => rightRightFunction1Lifting }
  
  private[psbp] def rightFunction1Lifting[Y] : Function1Lifting[[X] =>> Product[L, R][Y, X]] =
    new {
      override def lift1[Z, W]
        : (Z => W) => Product[L, R][Y, Z] => Product[L, R][Y, W] = { `z=>w` =>
          and(
            leftRightFunction1Lifting.lift1(`z=>w`), 
            rightRightFunction1Lifting.lift1(`z=>w`)
            )
        }
    }


// import psbp.external.specifcation.aggregatable.rec.RecStructureToRecReducer

given productRecAggregatable[
  L[+ _, + _]: Function1LiftingAtLeft: Function1LiftingAtRight, // : RecStructureToRecReducer,
  R[+ _, + _]: Function1LiftingAtLeft: Function1LiftingAtRight, // : RecStructureToRecReducer,
  C[+ _]: Function0Lifting: Function1Lifting: FunctionLifting
        : [C[+ _]] =>> RecReducerLifting[L, C]
        : [C[+ _]] =>> RecReducerLifting[R, C]]: RecAggregatable[Product[L, R], C] 
  // with RecStructureToRecReducer[Product[L, R]]        
  with RecReducerLifting[Product[L, R], C]
  with RecInitialTraverser[C] 
  with RecInitialReducer[Product[L, R]] 
  with RecFunctionLevelFusing[Product[L, R]] with

  // private[psbp] val leftRecStructureToRecReducer = summon[RecStructureToRecReducer[L]]
  // private[psbp] val rightRecStructureToRecReducer = summon[RecStructureToRecReducer[R]] 
  
  // import leftRecStructureToRecReducer.{ Structure => LeftStructure, structureToReducer => leftStructureToReducer }
  // import rightRecStructureToRecReducer.{ Structure => RightStructure, structureToReducer => rightStructureToReducer }

  // // override private[psbp] type Structure[Y, X] = ProductStructure[L, R][Y, X]
  // override type Structure[Y, X] = Reducer[Y, X] // ProductStructure[L, R][Y, X]

  // override def structureToReducer[Y, X]: Structure[Y, X] => Reducer[Y, X] =
  //   identity
    
  private val functionLifting: FunctionLifting[C] = summon[FunctionLifting[C]]
  import functionLifting.liftAnd

  private val leftSwapping = summon[RecReducerLifting[L, C]]
  private val rightSwapping = summon[RecReducerLifting[R, C]] 

  import leftSwapping.{ swap => lSwap }
  import rightSwapping.{ swap => rSwap }

  override private[psbp] def swap[Y, X]: Product[L, R][C[Y], C[X]] => C[Product[L, R][Y, X]] = 
    liftAnd(lSwap, rSwap)     
