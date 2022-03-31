package ec.edu.uasb.principal.entities;

import ec.edu.uasb.principal.entities.PrinPersona;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(PrinBanco.class)
public class PrinBanco_ { 

    public static volatile SingularAttribute<PrinBanco, Short> banCodigo;
    public static volatile SingularAttribute<PrinBanco, String> banNombre;
    public static volatile CollectionAttribute<PrinBanco, PrinPersona> prinPersonaCollection;

}