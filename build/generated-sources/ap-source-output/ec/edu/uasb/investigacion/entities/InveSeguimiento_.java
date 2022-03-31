package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.investigacion.entities.InveTipoSeguimiento;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InveSeguimiento.class)
public class InveSeguimiento_ { 

    public static volatile SingularAttribute<InveSeguimiento, Date> segFechaInicial;
    public static volatile SingularAttribute<InveSeguimiento, String> segEstadoReg;
    public static volatile SingularAttribute<InveSeguimiento, InveTipoSeguimiento> inveTipoSeguimiento;
    public static volatile SingularAttribute<InveSeguimiento, String> segNotifAprobado;
    public static volatile SingularAttribute<InveSeguimiento, Date> segFechaComunic;
    public static volatile SingularAttribute<InveSeguimiento, String> segTipoComunic;
    public static volatile SingularAttribute<InveSeguimiento, String> segNroComunic;
    public static volatile SingularAttribute<InveSeguimiento, BigDecimal> segMonto;
    public static volatile SingularAttribute<InveSeguimiento, Date> segFechaLimite;
    public static volatile SingularAttribute<InveSeguimiento, InveProyecto> inveProyecto;
    public static volatile SingularAttribute<InveSeguimiento, Long> lecCodigo;
    public static volatile SingularAttribute<InveSeguimiento, Long> segCodigo;
    public static volatile SingularAttribute<InveSeguimiento, String> segUsuarioCrea;
    public static volatile SingularAttribute<InveSeguimiento, Date> segFechaCrea;

}