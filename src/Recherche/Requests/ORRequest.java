package Recherche.Requests;

import Multimedia.Multimedia;
import Recherche.Composite.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.TreeSet;

public class ORRequest extends Requete {

    @Override
    public Set<Multimedia> execute(Set<Multimedia> multimedias) throws InvocationTargetException, IllegalAccessException {
        Set<Multimedia> result = new TreeSet<>();
        for (Component component: childComponents ) {
            result.addAll(component.execute(multimedias));
        }
        return result;
    }
}
