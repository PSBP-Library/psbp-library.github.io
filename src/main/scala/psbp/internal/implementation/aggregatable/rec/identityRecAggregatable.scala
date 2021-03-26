
package psbp.internal.implementation.aggregatable.rec

import psbp.internal.specification.lifting.{ Function0Lifting, Function1Lifting, Function1LiftingAtRight , Function1LiftingAtLeft }

import psbp.external.specifcation.types.Rec


import psbp.internal.specification.aggregatable.rec.{ RecReducerLifting, RecAggregatable, RecInitialTraverser, RecInitialReducer, RecFunctionLevelFusing }

// import psbp.external.specifcation.aggregatable.rec.RecStructureToRecReducer

given identityFunction1LiftingAtLeft: Function1LiftingAtLeft[Identity] with

  override private[psbp] def leftFunction1Lifting[X]: Function1Lifting[[Z] =>> Identity[Z, X]] =
    new {
      override def lift1[Z, Y]: (Z => Y) => Identity[Z, X] => Identity[Y, X] =
        identity
    } 

given identityFunction1LiftingAtRight: Function1LiftingAtRight[Identity] with

  override private[psbp] def rightFunction1Lifting[X]: Function1Lifting[[Z] =>> Identity[X, Z]] =
    new {
      override def lift1[Z, Y]: (Z => Y) => Identity[X, Z] => Identity[X, Y] =
        `z=>y` => 
          identity    
    }
          
// import psbp.external.specifcation.aggregatable.rec.RecStructureToRecReducer

given identityRecAggregatable[C[+ _]: Function0Lifting: Function1Lifting]: RecAggregatable[Identity, C] 
  // with RecStructureToRecReducer[Identity]
  with RecReducerLifting[Identity, C]
  with RecInitialTraverser[C] 
  with RecInitialReducer[Identity] 
  with RecFunctionLevelFusing[Identity] with

  // override private[psbp] type Structure[Y, X] = IdentityStructure[Y, X]
  // override type Structure[Y, X] = Reducer[Y, X] // IdentityStructure[Y, X]

  // override def structureToReducer[Y, X]: Structure[Y, X] => Reducer[Y, X] =
  //   identity

  override private[psbp] def swap[Y, X]: Identity[C[Y], C[X]] => C[Identity[Y, X]] =
    identity
