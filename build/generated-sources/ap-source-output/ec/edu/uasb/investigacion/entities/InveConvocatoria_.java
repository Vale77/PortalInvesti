package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.investigacion.entities.InveConvocatGrupo;
import ec.edu.uasb.investigacion.entities.InveTipoConvocatoria;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InveConvocatoria.class)
public class InveConvocatoria_ { 

    public static volatile SingularAttribute<InveConvocatoria, String> cvoUsuarioCrea;
    public static volatile CollectionAttribute<InveConvocatoria, InveConvocatGrupo> inveConvocatGrupoCollection;
    public static volatile SingularAttribute<InveConvocatoria, Long> cvoCodigo;
    public static volatile SingularAttribute<InveConvocatoria, Short> cvoAnioAcad;
    public static volatile SingularAttribute<InveConvocatoria, Date> cvoFechaCrea;
    public static volatile SingularAttribute<InveConvocatoria, String> cvoUsuarioAct;
    public static volatile SingularAttribute<InveConvocatoria, Date> cvoFechaEstado;
    public static volatile SingularAttribute<InveConvocatoria, Date> cvoFechaFin;
    public static volatile SingularAttribute<InveConvocatoria, String> cvoEstado;
    public static volatile SingularAttribute<InveConvocatoria, InveTipoConvocatoria> inveTipoConvocatoria;
    public static volatile SingularAttribute<InveConvocatoria, Date> cvoFechaAct;
    public static volatile SingularAttribute<InveConvocatoria, Date> cvoFechaInicio;

}