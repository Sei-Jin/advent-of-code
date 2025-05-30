package aoc.runner;

import aoc.Solver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

record Solution(int year, int day) {
    
    public Solver<?, ?> construct(String input) {
        var classPath = determineClassPath();
        var clazz = determineClass(classPath);
        var constructor = getConstructor(clazz);
        return createInstance(constructor, input);
    }
    
    private String determineClassPath() {
        var innerDirectories = String.format("aoc/event/year%d", year());
        var dayPackageName = innerDirectories.replace('/', '.');
        return String.format("%s.Day%02d", dayPackageName, day());
    }
    
    private static Class<?> determineClass(String classPath) {
        try {
            return Class.forName(classPath);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    private static Constructor<?> getConstructor(Class<?> clazz) {
        try {
            return clazz.getConstructor(String.class);
        }
        catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    
    private static Solver<?, ?> createInstance(Constructor<?> executable, String input) {
        try {
            return (Solver<?, ?>) executable.newInstance(input);
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
