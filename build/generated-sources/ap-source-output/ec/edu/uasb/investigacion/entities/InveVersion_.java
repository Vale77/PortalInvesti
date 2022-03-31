package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.investigacion.entities.InveProyecto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InveVersion.class)
public class InveVersion_ { 

    public static volatile SingularAttribute<InveVersion, String> verFechaRecep;
    public static volatile SingularAttribute<InveVersion, String> verFechaMaxEntrega;
    public static volatile SingularAttribute<InveVersion, InveProyecto> inveProyecto;
    public static volatile SingularAttribute<InveVersion, String> verNroOficioMaxEntrega;
    public static volatile SingularAttribute<InveVersion, String> verObservacionesRecep;
    public static volatile SingularAttribute<InveVersion, Integer> verCodigo;
    public static volatile SingularAttribute<InveVersion, String> verRecepProyecto;
    public static volatile SingularAttribute<InveVersion, String> verUsuarioRecep;

}