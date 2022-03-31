package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.principal.entities.PrinTipoDedicacion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InveInformeOper.class)
public class InveInformeOper_ { 

    public static volatile SingularAttribute<InveInformeOper, Long> pryCodigo;
    public static volatile SingularAttribute<InveInformeOper, String> iopUsuarioCrea;
    public static volatile SingularAttribute<InveInformeOper, Short> iopHorasEstimSeman;
    public static volatile SingularAttribute<InveInformeOper, PrinTipoDedicacion> prinTipoDedicacion;
    public static volatile SingularAttribute<InveInformeOper, Date> iopFechaCrea;
    public static volatile SingularAttribute<InveInformeOper, String> iopUsuarioAct;
    public static volatile SingularAttribute<InveInformeOper, Double> iopMesesEstim;
    public static volatile SingularAttribute<InveInformeOper, Date> iopFechaAct;
    public static volatile SingularAttribute<InveInformeOper, InveProyecto> inveProyecto;

}