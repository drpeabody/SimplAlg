/**
  * Created by Abhishek on 9/18/2017.
  */
class Term (private val factors: List[Scalar]){

  def equivalentTo(t: Term): Boolean = containsTerm(t) && t.containsTerm(this)

  def mergeWith(t: Term): Term = {
    val l = (t.filterScalar(Value, default = true).getFactors ++ filterScalar(Value, default = true).getFactors).map {
      case (x: Value) => x.getValue
      case _ => 1.0
    }.fold(1.0)((x, y) => x * y)
    Term(Value(l) :: t.filterScalar(Value, default = false).getFactors ++ filterScalar(Value, default = false).getFactors)
  }

  def containsTerm(t: Term): Boolean = t.getFactors match{
    case Nil => true
    case (_: Value) :: xs => containsTerm(Term(xs))
    case x :: xs => if(containsScalar(x)) containsTerm(Term(xs)) else false
  }

  def containsScalar(s: Scalar): Boolean = {
    factors.count(x => x.equivalentTo(s)) > 0
  }

  def filterScalar[T](ty: T, default: Boolean): Term = {
    val n = ty.getClass.getTypeName
    Term(factors.filter(x => n.startsWith(x.getClass.getTypeName) == default))
  }

  def getFactors: List[Scalar] = factors

  override def toString: String = factors.map(x => x.toString).fold("")((x, y) => x + y)

  def drop(s: Scalar): Term = new Term(factors.filterNot(x => x.equals(s)))

  def getConstFactor: Double = {
    factors.map {
      case (x: Value) => x.getValue
      case _ => 1.0
    }.fold(1.0)((x, y) => x * y)
  }

  def appendTo(e: Expression): Expression = new Expression(this :: e.getTerms)
}
object Term{

  def unapply(term: Term): Option[List[Scalar]] = term.getFactors match {
    case Nil => None
    case x => Some(x)
  }
  def apply(l: List[Scalar]): Term = new Term(l)

}
