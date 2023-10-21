class ThreeDBeing implements ThreeD {
    int dimA, dimB, dimC;

    float getLength() {
        return dimA;
    }

    public float getArea() {
        return dimA * dimB;
    }

    public float getVolume() {
        return dimA * dimB * dimC;
    }
}

class sphere {
    
}

interface OneD {
    float getLength();
}

interface TwoD {
    float getArea();
} 

interface ThreeD extends TwoD {
    float getVolume();
}

public class InterfaceExample {
    public static float getVolume(ThreeD object) {
        return object.getVolume();
    }
    public static void main(String args[]) {
        ThreeDBeing object = new ThreeDBeing();
        System.out.println(getVolume(object));
    }
}
