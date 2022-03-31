package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.principal.entities.PrinPersona;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InveLectorContratado.class)
public class InveLectorContratado_ { 

    public static volatile SingularAttribute<InveLectorContratado, Long> perCodigo;
    public static volatile SingularAttribute<InveLectorContratado, PrinPersona> prinPersona;
    public static volatile SingularAttribute<InveLectorContratado, Date> lcoFechaCrea;
    public static volatile SingularAttribute<InveLectorContratado, String> lcoUsuarioCrea;

}