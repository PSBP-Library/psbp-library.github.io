
package psbp.internal.implementation.aggregatable.rec

// import psbp.external.specifcation.aggregatable.Aggregatable

import psbp.internal.specification.lifting.{ Function0Lifting, Function1Lifting, Function1LiftingAtRight , Function1LiftingAtLeft }

// import psbp.internal.specification.rec.Rec

import psbp.external.specifcation.types.Rec

// import psbp.internal.specification.rec.{ rec }

import psbp.internal.specification.aggregatable.rec.{ RecReducerLifting, RecAggregatable, RecInitialTraverser, RecInitialReducer, RecFunctionLevelFusing }

// type Constant[+Y, +X] = X

// def mkConstant[Y, X]: X => Constant[Y, X] =
//   x =>
//     x

given constantFunction1LiftingAtLeft: Function1LiftingAtLeft[Constant] with

  override private[psbp] def leftFunction1Lifting[X]: Function1Lifting[[Z] =>> Constant[Z, X]] =
    new {
      override def lift1[Z, Y]: (Z => Y) => Constant[Z, X] => Constant[Y, X] =
        `z=>y` => 
          identity
    } 

given constantFunction1LiftingAtRight: Function1LiftingAtRight[Constant] with

  override private[psbp] def rightFunction1Lifting[X]: Function1Lifting[[Z] =>> Constant[X, Z]] =
    new {
      override def lift1[Z, Y]: (Z => Y) => Constant[X, Z] => Constant[X, Y] =
        identity
    }
   
// import psbp.external.specifcation.aggregatable.rec.RecStructureToRecReducer

given constantRecAggregatable[C[+ _]: Function0Lifting: Function1Lifting]: RecAggregatable[Constant, C] 
  // with RecStructureToRecReducer[Constant]
  with RecReducerLifting[Constant, C]
  with RecInitialTraverser[C] 
  with RecInitialReducer[Constant] 
  with RecFunctionLevelFusing[Constant] with

  // override private[psbp] type Structure[Y, X] = ConstantStructure[Y, X]
  // override type Structure[Y, X] = Reducer[Y, X] // ConstantStructure[Y, X]

  // override def structureToReducer[Y, X]: Structure[Y, X] => Reducer[Y, X] =
  //   identity

  override private[psbp] def swap[Y, X]: Constant[C[Y], C[X]] => C[Constant[Y, X]] =
    identity

