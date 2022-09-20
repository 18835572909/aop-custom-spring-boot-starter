package com.rhb.spring.aop.function;

import java.util.function.Function;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/9/20 16:11
 */
public class JdkFunctionTest {

  /*
  name	          type	            description
  Consumer	      Consumer< T >     接收T对象，不返回值
  Predicate	      Predicate< T >	  接收T对象并返回boolean
  Function	      Function< T, R >	接收T对象，返回R对象
  Supplier	      Supplier< T >	    提供T对象（例如工厂），不接收值
  UnaryOperator	  UnaryOperator	    接收T对象，返回T对象
  BinaryOperator	BinaryOperator	  接收两个T对象，返回T对象
  */

  public static void main(String[] args) {
    convert("张三,30",s -> s.split(",")[1],Integer::parseInt,integer -> integer+70);
    convert("张三,30",s -> Integer.parseInt(s.split(",")[1]),integer -> integer+70);
    convert("张三,30",s -> Integer.parseInt(s.split(",")[1])+70);
  }
  public static void convert(String s, Function<String,String> fun1,Function<String ,Integer>fun2,Function<Integer,Integer>fun3){
    Integer apply = fun1.andThen(fun2).andThen(fun3).apply(s);
    System.out.println(apply);
  }
  public static void convert(String s, Function<String,Integer>fun1,Function<Integer ,Integer>fun2){
    Integer apply = fun1.andThen(fun2).apply(s);
    System.out.println(apply);
  }
  public static void convert(String s, Function<String,Integer>fun1){
    Integer apply = fun1.apply(s);
    System.out.println(apply);
  }

}
