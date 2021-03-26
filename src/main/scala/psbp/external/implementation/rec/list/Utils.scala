package psbp.implementation.rec.list

import scala.collection.immutable

import psbp.external.specifcation.types.{ &&, || }

import psbp.external.specifcation.function.{ foldSum, `z=>(z||y)`, `y=>(z||y)` }

import psbp.external.specifcation.types.Rec

import psbp.internal.specification.rec.{ rec }

import psbp.internal.implementation.aggregatable.rec.mkNone

def recList[Z]: List[Z, RecList[Z]] => RecList[Z] = Rec(_)

def emptyRecList[Z]: RecList[Z] =
  recList[Z](`z=>(z||y)`(()))

def consRecList[Z](z: Z, zs: RecList[Z]): RecList[Z] =
  recList[Z](`y=>(z||y)`((zs, z)))

// extension [Z] (z: Z) def ::(zs: RecList[Z]): RecList[Z] =
//   consRecList(z, zs)  
  
def fromSeq[Z]: immutable.Seq[Z] => RecList[Z] = 
  _.foldRight(emptyRecList)(consRecList[Z])

def emptySeq[Z]: immutable.Seq[Z] = 
  immutable.Seq()

def consSeq[Z]: immutable.Seq[Z] && Z => immutable.Seq[Z] =
  case (z, zs) => 
    zs +: z

def toSeq[Z]: RecList[Z] => immutable.Seq[Z] = 
  rec apply (foldSum(_ => emptySeq[Z], consSeq[Z]))

  