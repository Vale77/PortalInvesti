package ec.edu.uasb.principal.entities;

import ec.edu.uasb.principal.entities.PrinPersona;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(PrinDiscapacidad.class)
public class PrinDiscapacidad_ { 

    public static volatile SingularAttribute<PrinDiscapacidad, Short> disCodigo;
    public static volatile CollectionAttribute<PrinDiscapacidad, PrinPersona> prinPersonaCollection;
    public static volatile SingularAttribute<PrinDiscapacidad, String> disNombre;

}