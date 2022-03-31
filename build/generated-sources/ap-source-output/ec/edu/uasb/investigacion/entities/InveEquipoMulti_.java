package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.investigacion.entities.InveEquipoMultiPK;
import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.investigacion.entities.InveRol;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InveEquipoMulti.class)
public class InveEquipoMulti_ { 

    public static volatile SingularAttribute<InveEquipoMulti, InveEquipoMultiPK> inveEquipoMultiPK;
    public static volatile SingularAttribute<InveEquipoMulti, Date> equFechaCrea;
    public static volatile SingularAttribute<InveEquipoMulti, InveProyecto> inveProyecto;
    public static volatile SingularAttribute<InveEquipoMulti, InveRol> inveRol;
    public static volatile SingularAttribute<InveEquipoMulti, Short> pryAreaAcad;
    public static volatile SingularAttribute<InveEquipoMulti, String> equUsuarioCrea;

}