package psbp.external.specification.function

import scala.collection.immutable.Seq


import psbp.external.specification.types.{ 
  &&
  , ||
}

// functional

def `z=>z`[Z]: Z => Z = 
  z =>
    z 

def `z=>u`[Z]: Z => Unit = 
  z =>
    () 
    
def `u=>u`: Unit => Unit = 
  `z=>z`[Unit]     

// construction

def `(z&&y)=>z`[Z, Y]: (Z && Y) => Z =
  (z, _) => 
    z

def `(z&&y)=>y`[Z, Y]: (Z && Y) => Y =
  (_, y) => 
    y

def `z=>(z&&z)`[Z]: Z => (Z && Z) =
  z =>
    (z, z)   

def `(z&&y&&x)=>(y&&x)`[Z, Y, X]: (Z && Y && X) => (Y && X) =
  case ((_, y), x) =>
    (y, x)  

def `z=>(z&&u)`[Z]: Z => (Z && Unit) =
  z =>
    (z, ()) 

def `(y&&u)=>y`[Y]: (Y && Unit) => Y =
  (y, _) => 
    y       

def `(z=>y)=>((z&&x)=>(y&&x)))`[Z, Y, X]: (Z => Y) => ((Z && X) => (Y && X)) =
  `z=>y` => 
    (z, x) =>
      (`z=>y`(z), x)

def `(z&&y)=>(z&&y)`[Z, Y]: (Z && Y) => (Z && Y) =
  identity

def unfoldProduct[Z, Y, X]: ((Z => Y) && (Z => X)) => (Z => (Y && X)) =
  (`z=>y`, `z=>x`) => 
    z =>
      (`z=>y`(z), `z=>x`(z))

def and[Z, Y, X, W]: ((Z => X) && (Y => W)) => (Z && Y) => (X && W) =
  (`z=>x`, `y=>w`) =>
    unfoldProduct(`(z&&y)=>z` andThen `z=>x`, `(z&&y)=>y` andThen `y=>w`)
    
// condition

import ||.{ Left, Right }

def `z=>(z||y)`[Z, Y]: Z => (Z || Y) =
  z =>
    Left(z)

def `y=>(z||y)`[Z, Y]: Y => (Z || Y) =
  y =>
    Right(y)   

def foldSum[Z, Y, X]: ((Y => Z) && (X => Z)) => (Y || X) => Z =
  (`y=>z`, `x=>z`) =>
    _.foldSum(`y=>z`, `x=>z`)

def `(z||z)=>z`[Z]: (Z || Z) => Z =
  foldSum(z => z, z => z)  
  
def `(y||x)=>b`[Y, X]: (Y || X) => Boolean =
  foldSum(_ => true, _ => false)

def `(y||x)=>y`[Y, X]: (Y || X) => Y =
  foldSum(y => y, _ => ???) 

def `(y||x)=>x`[Y, X]: (Y || X) => X =
  foldSum(_ => ???, x => x) 

def or[Z, Y, X, W]: ((X => Z) && (W => Y)) => (X || W) => (Z || Y) =
  (`x=>z`, `w=>y`) =>
    foldSum(`x=>z` andThen `z=>(z||y)`, `w=>y` andThen `y=>(z||y)`)   

// construction and condition

def `(z&&b)=>(z||z)`[Z]: (Z && Boolean) => (Z || Z) =
  (z, b) => 
    (z, b) match {
      case (_, true) => Left(z)
      case (_, false) => Right(z) 
    }    

// other

def foldSeq[Z, Y]: (Y && ((Z && Y) => Y)) => (Seq[Z] => Y) =
  (y, `(z&&y)=>y`) =>
    _.foldRight(y){ (z, y) => `(z&&y)=>y`((z, y)) }

