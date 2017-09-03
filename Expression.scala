/**
  * Created by Abhishek on 9/3/2017.
  */
class Expression(private val terms: List[List[Scalar]]) extends Scalar(terms.length){
  override def toString: String = {
    terms.map(x => x.map(y => y.toString).fold("")((z: String, w: String) => z + w)).fold("")((x: String, w: String) => x + " + " + w).substring(3)
  }

  def reduceValuedTerms(): Expression = {
    val l = terms.filter(x => (x.length == 1 && x(0).isInstanceOf[Value])).map(x => x.asInstanceOf[Value].getValue).fold(0)((x, y) => x + y)
    new Expression(List(l) :: (terms.filter(x => (x.length == 1 && x(0).isInstanceOf[Value])).toList))
  }



}
