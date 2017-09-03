/**
  * Created by Abhishek on 9/3/2017.
  */
abstract class Scalar(private val length: Int) {
  def toString: String
  def getLength: Int = length
}
