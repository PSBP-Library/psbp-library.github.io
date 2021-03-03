package examples.specification.functional

import scala.language.postfixOps

import psbp.specification.types.&&

import psbp.specification.program.Functional

import examples.specification.function

def isZero[>-->[- _, + _]: Functional]: BigInt >--> Boolean =  
  function.isZero asProgram

def one[>-->[- _, + _]: Functional, Z]: Z >--> BigInt =
  function.one asProgram

def subtractOne[>-->[- _, + _]: Functional]: BigInt >--> BigInt =
  function.subtractOne asProgram

def multiply[>-->[- _, + _]: Functional]: (BigInt && BigInt) >--> BigInt =  
  function.multiply asProgram

def zero[>-->[- _, + _]: Functional, Z]: Z >--> BigInt =
  function.zero asProgram  

def isOne[>-->[- _, + _]: Functional]: BigInt >--> Boolean =  
  function.isOne asProgram  

def subtractTwo[>-->[- _, + _]: Functional]: BigInt >--> BigInt =
  function.subtractTwo asProgram  

def add[>-->[- _, + _]: Functional]: (BigInt && BigInt) >--> BigInt =  
  function.add asProgram 
  
def isNotNegative[>-->[- _, + _]: Functional]: BigInt >--> Boolean =  
  function.isNotNegative asProgram 

def negate[>-->[- _, + _]: Functional]: BigInt >--> BigInt =
  function.negate asProgram   
  