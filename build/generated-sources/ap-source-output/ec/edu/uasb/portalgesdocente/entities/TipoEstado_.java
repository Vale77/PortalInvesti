package ec.edu.uasb.portalgesdocente.entities;

import ec.edu.uasb.portalgesdocente.entities.EstadoSolicContrato;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(TipoEstado.class)
public class TipoEstado_ { 

    public static volatile CollectionAttribute<TipoEstado, EstadoSolicContrato> estadoSolicContratoCollection;
    public static volatile SingularAttribute<TipoEstado, String> staSiguienteProceso;
    public static volatile SingularAttribute<TipoEstado, String> staDescripcion;
    public static volatile SingularAttribute<TipoEstado, Integer> staOrden;
    public static volatile SingularAttribute<TipoEstado, String> staSiguientePerfil;
    public static volatile SingularAttribute<TipoEstado, Long> staFirmaUsrSigPerfil;
    public static volatile SingularAttribute<TipoEstado, String> staCodigo;

}