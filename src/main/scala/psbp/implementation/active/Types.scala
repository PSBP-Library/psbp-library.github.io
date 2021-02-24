package psbp.implementation.active

type Active[+Y] = Y

type `=>A`[-Z, +Y] = Z => Active[Y]

