/**
  * Created by Abhishek on 9/3/2017.
  */
class Expression(private val terms: List[Term]){

  override def toString: String = {
    terms.map(x => x.getFactors.map(y => y.toString).fold("")((z: String, w: String) => z + w)).fold("")((x: String, w: String) => x + " + " + w).substring(3)
  }

  def getTerms: List[Term] = terms

  def reduceValuedTerms(): Expression = {
    val l = terms.map(x => x.getFactors).map {
      case (y: Value) :: Nil => y.getValue
      case _ => 0.0
    }.fold(0.0)(op = (x, y) => x + y)
    new Expression(Term(List(new Value(l))) :: terms.filter(x => x match {
      case Term((_: Value) :: Nil) => false
      case _ => true
    }))
  }

  def reduceSimilarTerms(): Expression = terms match {
    case Nil => Expression(List(List()))
    case x :: xs => xs.find(y => y.equivalentTo(x)) match {
      case None => new Expression(x :: new Expression(xs).reduceSimilarTerms().getTerms)
      case Some(y) =>
        val v = getConstOfTermsLike(y)
        val ex = this filterTermsEquivalentTo (y, default = false)
        v appendTo y.filterScalar(Value, false) appendTo ex
    }
  }

  def filterTermsEquivalentTo(t: Term, default: Boolean): Expression = {
    new Expression(terms.filter(x => (x equivalentTo t) == default))
  }

  def getConstOfTermsLike(t: Term): Value = {
    Value(terms.filter(x => x.equivalentTo(t)).map(x => x.getConstFactor).fold(0.0)((x, y) => x + y))
  }

  def drop(t: Term): Expression = new Expression(terms.filterNot(x => x.equals(t)))
}
object Expression{
  def apply(t: List[List[Scalar]]): Expression = new Expression(t.map(x => Term(x)))
}
