package ec.edu.uasb.principal.entities;

import ec.edu.uasb.principal.entities.PrinEntidad;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(PrinTipoEntidad.class)
public class PrinTipoEntidad_ { 

    public static volatile CollectionAttribute<PrinTipoEntidad, PrinEntidad> prinEntidadCollection;
    public static volatile SingularAttribute<PrinTipoEntidad, Short> tenCodigo;
    public static volatile SingularAttribute<PrinTipoEntidad, String> tenAbrev;
    public static volatile SingularAttribute<PrinTipoEntidad, String> tenNombre;

}