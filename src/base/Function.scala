package base

/**
  * Created by Abhishek on 12/3/2017.
  */
abstract class Function extends Scalar{
  def operate(parent: Term): Term
}
