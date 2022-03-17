package Recherche.Composite;

import Multimedia.Multimedia;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public interface Component {

    Set<Multimedia> execute(Set<Multimedia> multimedias) throws InvocationTargetException, IllegalAccessException;

}
