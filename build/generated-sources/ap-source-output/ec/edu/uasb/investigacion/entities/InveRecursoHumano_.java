package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.investigacion.entities.InveRecursoHumanoPK;
import ec.edu.uasb.investigacion.entities.InveRol;
import ec.edu.uasb.principal.entities.PrinPersona;
import ec.edu.uasb.principal.entities.PrinTipoDedicacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InveRecursoHumano.class)
public class InveRecursoHumano_ { 

    public static volatile SingularAttribute<InveRecursoHumano, Short> rhuNumeroMeses;
    public static volatile SingularAttribute<InveRecursoHumano, PrinPersona> prinPersona;
    public static volatile SingularAttribute<InveRecursoHumano, PrinTipoDedicacion> prinTipoDedicacion;
    public static volatile SingularAttribute<InveRecursoHumano, InveRecursoHumanoPK> inveRecursoHumanoPK;
    public static volatile SingularAttribute<InveRecursoHumano, InveProyecto> inveProyecto;
    public static volatile SingularAttribute<InveRecursoHumano, InveRol> inveRol;

}