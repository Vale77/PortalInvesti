package ec.edu.uasb.portalgesdocente.entities;

import ec.edu.uasb.portalgesdocente.entities.ContratoDocente;
import ec.edu.uasb.portalgesdocente.entities.EstadoSolicContratoPK;
import ec.edu.uasb.portalgesdocente.entities.TipoEstado;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(EstadoSolicContrato.class)
public class EstadoSolicContrato_ { 

    public static volatile SingularAttribute<EstadoSolicContrato, Date> escFecha;
    public static volatile SingularAttribute<EstadoSolicContrato, EstadoSolicContratoPK> estadoSolicContratoPK;
    public static volatile SingularAttribute<EstadoSolicContrato, String> escObservacion;
    public static volatile SingularAttribute<EstadoSolicContrato, TipoEstado> tipoEstado;
    public static volatile SingularAttribute<EstadoSolicContrato, ContratoDocente> contratoDocente;
    public static volatile SingularAttribute<EstadoSolicContrato, Long> escCodigoUsr;

}