package base

/**
  * Created by Abhishek on 9/3/2017.
  */
abstract class Scalar {
  def toString: String
  def equivalentTo(s: Scalar): Boolean

  def productWith(t: Term): Term
  def isPresentInTerm(t: List[Scalar]): Boolean = t.count(x => x.equivalentTo(this)) > 0
}