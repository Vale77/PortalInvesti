package ec.edu.uasb.portalgesdocente.entities;

import ec.edu.uasb.portalgesdocente.entities.ContratoDocente;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(RolDocente.class)
public class RolDocente_ { 

    public static volatile SingularAttribute<RolDocente, String> rdoCodigo;
    public static volatile CollectionAttribute<RolDocente, ContratoDocente> contratoDocenteCollection;
    public static volatile SingularAttribute<RolDocente, String> rdoDescripcion;
    public static volatile SingularAttribute<RolDocente, Short> codNivelAcad;
    public static volatile SingularAttribute<RolDocente, String> rdoEstado;

}