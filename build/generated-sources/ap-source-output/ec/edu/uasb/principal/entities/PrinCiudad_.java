package ec.edu.uasb.principal.entities;

import ec.edu.uasb.principal.entities.PrinCiudadPK;
import ec.edu.uasb.principal.entities.PrinPais;
import ec.edu.uasb.principal.entities.PrinPersona;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(PrinCiudad.class)
public class PrinCiudad_ { 

    public static volatile SingularAttribute<PrinCiudad, PrinCiudadPK> prinCiudadPK;
    public static volatile CollectionAttribute<PrinCiudad, PrinPersona> prinPersonaCollection;
    public static volatile SingularAttribute<PrinCiudad, PrinPais> prinPais;
    public static volatile SingularAttribute<PrinCiudad, String> ciuNombre;

}