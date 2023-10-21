/**
 * Container can contain a general type of data
 * which is Comparable interface type
 * 
 * In other words any class which implements 
 * Comparable can be assigned to data
 * 
 * Notice I am not providing generic type for comparable
 * This will not do compile error but it will be warning
 * 
 * In this case we can do type casting when getting the data
 * to avoid compiler error when assigning to object variable
 * 
 */
class Container {
    private Comparable<?> data;

    public Container(Comparable<?> data) {
        this.data = data;
    }

    public void changeData(Comparable<?> data) {
        this.data = data;
    }

    public Comparable<?> getData() {
        return this.data;
    }
}

/**
 * This class main method demonstrates 
 * achieving polymorphism of class through implemented interface
 */
public class PolymorphismExample {
    public static void main(String args[]) {
        // both String and Integer implements Comparable
        String str = "abc";
        Integer num = 10;
        // we can create Container from both num or str
        // as their type implements comparable
        Container container1 = new Container(num);
        Container container2 = new Container(str);
        // print the contents
        System.out.println("Before change container1 and container2:");
        System.out.println(container1.getData());
        System.out.println(container2.getData());

        System.out.println("Let's change the data then print container1 and container2");
        // lets assign the object to an interface which it implements
        // notice there is not complaint (except raw type, we can avoid that using wildcard)
        // using wildcard Comparable<?> comp = str;
        Comparable<?> comp = str;
        // for no warning Comparable<String> comp = str;
        // now let's change the data of container1 to contain str
        // notice that container1 does not care neither compiler?
        // because polymorphism allows this
        // you can refer an object through it's interface or superclass
        container1.changeData(comp);
        // let's print it
        // do you notice it is not giving any warning or error?
        // that's because whatever container1.getData returns
        // even if it is an interface the 
        // toString method associated to the object this interface is part of
        // will be called
        // 
        // this is an example of polymorphism,
        // we are calling unknown object type through it's interface 
        // similar can be done using superclass type, 
        // the method which should be called will be resolved runtime
        System.out.println(container1.getData());
        
        // let's change container2 to integer
        container2.changeData(num);

        // now let's get container2 data
        // let's avoid the raw type warning this type
        // it is not important here but let's write properly
        // there will be a type safety warning, we can avoid using wildcard
        // using wildcard Comparable<?> comp = str;
        Comparable<?> comp2 = container2.getData();
        System.out.println(comp2);
    }
}
