package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.investigacion.entities.InvePresupuesto;
import ec.edu.uasb.investigacion.entities.InveRubroGrupo;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InveRubro.class)
public class InveRubro_ { 

    public static volatile SingularAttribute<InveRubro, String> rubDescripcion;
    public static volatile SingularAttribute<InveRubro, BigDecimal> rubMonto;
    public static volatile SingularAttribute<InveRubro, Short> rubCodigo;
    public static volatile CollectionAttribute<InveRubro, InvePresupuesto> invePresupuestoCollection;
    public static volatile CollectionAttribute<InveRubro, InveRubroGrupo> inveRubroGrupoCollection;
    public static volatile SingularAttribute<InveRubro, String> rubNombre;

}