package base

/**
  * Created by Abhishek on 9/18/2017.
  */
class Term (private val coeff: Value, private val factors: List[Scalar]) extends Differentiable{
  //Assume factors doesn't contain any Vlue type. Filter Values from there during construction.

  def equivalentTo(t: Term): Boolean = containsTerm(t) && t.containsTerm(this)

  def mergeWith(t: Term): Term = Term(getCoeff * t.getCoeff, t.getFactors ++ getFactors)

  def containsTerm(t: Term): Boolean = t.getFactors match{
    case Nil => true
    case x :: xs => if(this containsScalar x) this containsTerm Term(xs) else false
  }

  def containsScalar(s: Scalar): Boolean = getFactors.count(x => x.equivalentTo(s)) > 0

  def filterScalar[T](ty: T, default: Boolean): Term = {
    val n = ty.getClass.getTypeName
    Term(getCoeff, factors.filter(x => n.startsWith(x.getClass.getTypeName) == default))
  }

  def getFactors: List[Scalar] = factors
  def getCoeff: Value = coeff

  override def toString: String = (if(getCoeff.getValue == 1.0) "" else getCoeff.toString) + factors.map(_.toString).fold("")((x, y) => x + y)

  def appendTo(e: Expression): Expression = new Expression(this :: e.getTerms)

  def derivativeWRT(x: Variable): Expression = new Expression(getFactors.map(
    t => {
      val l = Term(getCoeff,
        if(t.isInstanceOf[Differentiable]) {
          t.asInstanceOf[Differentiable].derivativeWRT(x) :: getFactors.filter(_ != t)}
        else List()
      )
      Term(l.getCoeff * l.getFactors.filter(g => g.isInstanceOf[Value]).map(g => g.asInstanceOf[Value]).fold(Value(1))(_ * _), l.filterScalar(Value, default = false).getFactors)
    }))
}
object Term{

  def unapply(term: Term): Option[List[Scalar]] = term.getFactors match {
    case Nil => None
    case _ => Some(term.getFactors)
  }
  def apply(x: List[Scalar]): Term = {
    Term(x.filter(s => s.isInstanceOf[Value]).map(s => s.asInstanceOf[Value]).fold(Value(1))(_ * _), x.filter(s => !s.isInstanceOf[Value]))
  }
  //Careful this is the only one without checks
  def apply(v: Value, l: List[Scalar]): Term = new Term(v, l)
  def apply(t: Scalar): Term = if(t.isInstanceOf[Value]) Term(t.asInstanceOf[Value], List()) else Term(Value(1), List(t))
  def apply(t: (Scalar, Scalar)): Term = Term(List(t._1, t._2))
  def apply(t: (Scalar, Scalar, Scalar)): Term = Term(List(t._1, t._2, t._3))
  def apply(t: (Scalar, Scalar, Scalar, Scalar)): Term = Term(List(t._1, t._2, t._3, t._4))
  def apply(a: Scalar, b: Scalar): Term = Term(List(a, b))
}
