/**
  * Created by Abhishek on 9/3/2017.
  */
abstract class Scalar(private val length: Int) {
  def toString: String
  def getLength: Int = length
  def equivalentTo(s: Scalar): Boolean

  def appendTo(t: Term): Term = new Term(this :: t.getFactors)
  def isPresentInTerm(t: List[Scalar]): Boolean = t.count(x => x.equivalentTo(this)) > 0
}