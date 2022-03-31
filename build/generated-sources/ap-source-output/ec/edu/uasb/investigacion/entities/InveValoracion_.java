package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.investigacion.entities.InveEstadoValoracion;
import ec.edu.uasb.investigacion.entities.InveLector;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InveValoracion.class)
public class InveValoracion_ { 

    public static volatile SingularAttribute<InveValoracion, String> valUsuarioCrea;
    public static volatile SingularAttribute<InveValoracion, String> valCorrespondencia;
    public static volatile SingularAttribute<InveValoracion, InveLector> inveLector;
    public static volatile SingularAttribute<InveValoracion, Date> valFechaCrea;
    public static volatile SingularAttribute<InveValoracion, String> valUsuarioAct;
    public static volatile SingularAttribute<InveValoracion, String> valAporteRelevante;
    public static volatile SingularAttribute<InveValoracion, InveEstadoValoracion> inveEstadoValoracion;
    public static volatile SingularAttribute<InveValoracion, Date> valFechaRecep;
    public static volatile SingularAttribute<InveValoracion, String> valRecomendaPublic;
    public static volatile SingularAttribute<InveValoracion, String> valUsuarioRecep;
    public static volatile SingularAttribute<InveValoracion, Date> valFechaAct;
    public static volatile SingularAttribute<InveValoracion, String> valRecomendacion;
    public static volatile SingularAttribute<InveValoracion, String> valComentario;
    public static volatile SingularAttribute<InveValoracion, Long> valCodigo;
    public static volatile SingularAttribute<InveValoracion, String> valImportancia;

}