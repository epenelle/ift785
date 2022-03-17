package Recherche.Composite;

import Multimedia.Multimedia;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class ConfigurablePredicate implements Component {

    public Method method;
    public Object parameter;
    public Predicat predicat;

    public ConfigurablePredicate(String method, Object parameter, Predicat predicat) throws NoSuchMethodException {
        this.method = predicat.getClass().getMethod(method, parameter.getClass());
        this.parameter = parameter;
        this.predicat = predicat;
    }

    public ConfigurablePredicate(String method, Predicat predicat) throws NoSuchMethodException {
        this.method = predicat.getClass().getMethod(method);
        this.predicat = predicat;
    }

    @Override
    public Set<Multimedia> execute(Set<Multimedia> multimediaSet) throws InvocationTargetException, IllegalAccessException {
        return (Set) method.invoke(predicat, parameter);
    }


}
