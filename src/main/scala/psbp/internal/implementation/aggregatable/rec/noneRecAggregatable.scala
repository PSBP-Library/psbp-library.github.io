package psbp.internal.implementation.aggregatable.rec

// import psbp.external.specifcation.aggregatable.Aggregatable

import psbp.internal.specification.lifting.{ Function0Lifting, Function1Lifting, Function1LiftingAtRight , Function1LiftingAtLeft }

import psbp.external.specifcation.types.Rec

// import psbp.internal.specification.rec.Rec

import psbp.internal.specification.aggregatable.rec.{ RecReducerLifting, RecAggregatable, RecInitialTraverser, RecInitialReducer, RecFunctionLevelFusing }

// type None[+Y, +X] = Unit

// def mkNone[Y, X]: None[Y, X] = ()

given noneFunction1LiftingAtLeft: Function1LiftingAtLeft[None] with

  override private[psbp] def leftFunction1Lifting[X]: Function1Lifting[[Z] =>> None[Z, X]] =
    new {
      override def lift1[Z, Y]: (Z => Y) => None[Z, X] => None[Y, X] =
        `z=>y` => 
          identity
    }     

given noneFunction1LiftingAtRight: Function1LiftingAtRight[None] with

  override private[psbp] def rightFunction1Lifting[X]: Function1Lifting[[Z] =>> None[X, Z]] =
    new {
      override def lift1[Z, Y]: (Z => Y) => None[X, Z] => None[X, Y] =
        `z=>y` => 
          identity
    }    
   

given noneRecAggregatable[C[+ _]: Function0Lifting: Function1Lifting]: RecAggregatable[None, C] 
  with RecReducerLifting[None, C]
  with RecInitialTraverser[C] 
  with RecInitialReducer[None] 
  with RecFunctionLevelFusing[None] with
  
  private val function0Lifting = summon[Function0Lifting[C]]
  import function0Lifting.lift0

  override private[psbp] def swap[Y, X]: None[C[Y], C[X]] => C[None[Y, X]] =
    lift0
