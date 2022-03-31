package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.investigacion.entities.InvePresupuestoPK;
import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.investigacion.entities.InveRubro;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InvePresupuesto.class)
public class InvePresupuesto_ { 

    public static volatile SingularAttribute<InvePresupuesto, String> preDescripcion;
    public static volatile SingularAttribute<InvePresupuesto, BigDecimal> preMontoGastado;
    public static volatile SingularAttribute<InvePresupuesto, InvePresupuestoPK> invePresupuestoPK;
    public static volatile SingularAttribute<InvePresupuesto, BigDecimal> preMontoAprob;
    public static volatile SingularAttribute<InvePresupuesto, InveProyecto> inveProyecto;
    public static volatile SingularAttribute<InvePresupuesto, InveRubro> inveRubro;

}