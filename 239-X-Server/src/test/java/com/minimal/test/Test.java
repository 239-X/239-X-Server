package com.minimal.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by administrator on 2019/4/24.
 */
public class Test {
    class Dog{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    public void testInt(int a){
        a=2;
        System.out.println("testInt - a:"+a);
    }

    public void testDog(Dog dog){
        dog.setName("testDog");
        System.out.println("testDog-"+dog.getName());
    }

    public Dog newDog(){
       return new Dog();
    }
    public static void main(String[] args) {
//        List<Dog> dogs = new ArrayList<>();
//        Test t = new Test();
//        int a = 3;
//        t.testInt(a);
//        System.out.println("main-a:"+a);
//        Dog dog = t.newDog();
//        dog.setName("mianDog");
//        t.testDog(dog);
//        dog = null;
//        dogs.add(dog);
//        dog = null;
//        System.out.println("mianDog-"+dogs.get(0).getName());
        double Xmx =3000,Xmn=500,SurvivorRatior=1;
        double CMSInitiatingOccupancyFraction;
        CMSInitiatingOccupancyFraction =((Xmx-Xmn)-(Xmn-Xmn/(SurvivorRatior+2)))/(Xmx-Xmn)*100;
        System.out.println(CMSInitiatingOccupancyFraction);
    }
}
