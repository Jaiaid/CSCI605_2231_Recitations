import java.lang.reflect.Array;
import java.util.*;

class ContainerSpecific {
    public Integer data;

    ContainerSpecific(Integer data) {
        this.data = data;
    }
}

class Container<T extends Comparable<T>> {
    public T data;

    Container() {
        this.data = null;
    }

    Container(T data) {
        this.data = data;
        data.compareTo(data);
    }
}

class ContainerType2<T1, T2> {
    public T1 data1;
    public T2 data2;

    ContainerType2(T1 data1, T2 data2) {
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
        Container<Integer> container2 = new Container<Integer>(10);
        System.out.println(container2.data);
        // lets create a container type 2 with with string nad integer
        ContainerType2<String, Integer> container3 = new ContainerType2<String, Integer>("Hi, hello world!", 10001);
        System.out.println(container3.data1);
        System.out.println(container3.data2);
        int[] ara = new int[10];
        ContainerSpecific contAra[] = new ContainerSpecific[10];
        Arrays.sort(contAra, 0, 0, null);
    }
}