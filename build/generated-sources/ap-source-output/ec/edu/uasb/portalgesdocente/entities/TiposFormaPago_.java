package ec.edu.uasb.portalgesdocente.entities;

import ec.edu.uasb.portalgesdocente.entities.ContratoDocente;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(TiposFormaPago.class)
public class TiposFormaPago_ { 

    public static volatile CollectionAttribute<TiposFormaPago, ContratoDocente> contratoDocenteCollection;
    public static volatile SingularAttribute<TiposFormaPago, Short> tfpCodigo;
    public static volatile SingularAttribute<TiposFormaPago, String> tfpDescripcion;

}