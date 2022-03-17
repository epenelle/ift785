package Recherche.Requests;

import Multimedia.Multimedia;
import Recherche.Composite.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.TreeSet;

public class ANDRequest extends Requete {

    @Override
    public Set<Multimedia> execute(Set<Multimedia> multimedias) throws InvocationTargetException, IllegalAccessException {
        Set<Multimedia> result = new TreeSet<>();

        boolean firstFlag = true;
        for (Component component: childComponents ) {
            /* NOTE : On doit d'abord assigner le resultat d'un predicat (addAll), sans quoi l'intersection
             * se ferait avec un ensemble vide (result initialise est vide).
             *  Ensuite, on retient l'intersection (retainAll) avec les resultats intermediaires.
             */
            if (firstFlag) {
                result.addAll(component.execute(multimedias));
                firstFlag = false;
            }
            result.retainAll(component.execute(multimedias));
        }
        return result;
    }
}
