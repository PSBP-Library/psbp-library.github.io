package examples.specification.functional

import scala.language.postfixOps

import psbp.external.specifcation.types.&&

import psbp.external.specifcation.program.Functional

import examples.specification.function

def isZero[
  >-->[- _, + _]: Functional
  ]: BigInt >--> Boolean =  
  function.isZero asProgram

def one[
  >-->[- _, + _]: Functional
  , Z
  ]: Z >--> BigInt =
  function.one asProgram

def subtractOne[
  >-->[- _, + _]: Functional
  ]: BigInt >--> BigInt =
  function.subtractOne asProgram

def multiply[
  >-->[- _, + _]: Functional
  ]: (BigInt && BigInt) >--> BigInt =  
  function.multiply asProgram

def zero[
  >-->[- _, + _]: Functional
  , Z
  ]: Z >--> BigInt =
  function.zero asProgram  

def isOne[
  >-->[- _, + _]: Functional
  ]: BigInt >--> Boolean =  
  function.isOne asProgram  

def subtractTwo[
  >-->[- _, + _]: Functional
  ]: BigInt >--> BigInt =
  function.subtractTwo asProgram  

def add[
  >-->[- _, + _]: Functional
  ]: (BigInt && BigInt) >--> BigInt =  
  function.add asProgram 
  
def isNegative[
  >-->[- _, + _]: Functional
  ]: BigInt >--> Boolean =  
  function.isNegative asProgram 

def negate[
  >-->[- _, + _]: Functional
  ]: BigInt >--> BigInt =
  function.negate asProgram   

def isPositive[>-->[- _, + _]: Functional]: BigInt >--> Boolean =  
  function.isPositive asProgram 

  