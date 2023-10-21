class Container<T> {
    public T data;

    Container(T data) {
        this.data = data;
    }
}

class ContainerType2<T1, T2> {
    public T1 data1;
    public T2 data2;

    Container(T1 data1, T2 data2) {
        this.data1 = data1;
        this.data2 = data2;
    }
}

public class GenericExample {
    public static void main(String args[]) {
        // raw type is allowed for  backward compatability
        // following will give warning
        Container container = new Container(10);
        System.out.println(container.data);
        // following will give error why
        Container<Integer> container2 = new Container<Integer>(100);
        System.out.println(container2.data);
        // lets create a container with string
        Container<String> container3 = new Container<String>("Hi, hello world!");
        System.out.println(container3.data);
    }
}