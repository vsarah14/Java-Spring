package com.example.CarRenting.model.iter.car;

import com.example.CarRenting.model.Car;
import com.example.CarRenting.model.iter.Container;
import com.example.CarRenting.model.iter.Iterator;

public class CarContainer  implements Container<Car> {

    private final Car[] cars;
    public CarContainer(Car[] cars) {
        this.cars = cars;
    }
    @Override
    public Iterator<Car> getIterator() {
        return new CarIterator();
    }

    private class CarIterator implements Iterator<Car>{
        private int index;
        private CarIterator(){
            this.index = 0;
        }
        @Override
        public boolean hasNext() {
            return index < cars.length;
        }

        @Override
        public Car next() {
            if (hasNext()){
                return cars[index++];
            }
            return null;
        }
    }
}
