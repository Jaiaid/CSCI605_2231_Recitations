import java.util.Vector;

class Container<T> {
    public T data;

    Container (T data){
        this.data = data;
    }
}

public class VectorUse {
          static void doTheWork()     {
              Vector<String> aStringVector    = new Vector<String>();
              Vector<Integer> aIntegerVector  = new Vector<Integer>();
              Vector<Object> aObjectVector    = new Vector<Object>();

              // 
              Container<String> c = new Container<>("ab");
              aStringVector.add("a");
              aIntegerVector.add(1);
              aObjectVector.add(new Object());
              aStringVector.add((String)(new Object()));

              // aStringVector.add( aObjectVector.firstElement() );
              aObjectVector.add( aStringVector.firstElement() );
          }
          public static void main(String args[] )     {
              VectorUse.doTheWork();
          }
     }

