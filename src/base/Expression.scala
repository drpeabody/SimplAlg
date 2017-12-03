package base

/**
  * Created by Abhishek on 9/3/2017.
  */
class Expression(private val terms: List[Term]) extends Scalar{

  override def toString: String = {
    terms.map(_.toString).fold("")(_ + " + " +_).substring(3)
  }

  def getTerms: List[Term] = terms

  def reduceValuedTerms(): Expression = {
    val l = terms.filter(_.getFactors == Nil).map(_.getCoeff).fold(Value(0.0))(_ + _)
    new Expression(Term(l, Nil) :: terms.filter(x => x.getFactors != Nil))
  }

  def reduceSimilarTerms(): Expression = terms match {
    case Nil => Expression(List(List()))
    case x :: xs => xs.find(y => y.equivalentTo(x)) match {
      case None => x.appendTo(new Expression(xs).reduceSimilarTerms())
      case Some(y) =>
        Term(getConstOfTermsLike(y), y.getFactors) appendTo filterTermsEquivalentTo (y, default = false)
    }
  }

  def filterTermsEquivalentTo(t: Term, default: Boolean): Expression = {
    new Expression(terms.filter(x => (x equivalentTo t) == default))
  }

  def getConstOfTermsLike(t: Term): Value = {
    Value(terms.filter(x => x.equivalentTo(t)).map(x => x.getCoeff.getValue).fold(0.0)((x, y) => x + y))
  }

  override def equivalentTo(s: Scalar): Boolean = ???

  override def productWith(t: Term): Term = ???
}
object Expression{
  def apply(t: List[List[Scalar]]): Expression = new Expression(t.map(x => Term(x)))
}
