package psbp.internal.specification.structure

import psbp.external.specification.program.Program

import psbp.external.specification.structure.Traversable

private[psbp] trait BiTraversable[A[+ _, + _], >-->[- _, + _]]:

  private[psbp] def leftTraversable[X]: Traversable[[Y] =>> A[Y, X], >-->] 

  private[psbp] def rightTraversable[Y]: Traversable[[X] =>> A[Y, X], >-->] 
