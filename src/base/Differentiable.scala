package base

/**
  * Created by Abhishek on 12/3/2017.
  */
abstract trait Differentiable {
  def derivativeWRT(X: Variable): Scalar
}
