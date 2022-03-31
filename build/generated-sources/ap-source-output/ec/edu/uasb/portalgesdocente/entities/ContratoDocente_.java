package ec.edu.uasb.portalgesdocente.entities;

import ec.edu.uasb.portalgesdocente.entities.RolDocente;
import ec.edu.uasb.portalgesdocente.entities.TiposFormaPago;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(ContratoDocente.class)
public class ContratoDocente_ { 

    public static volatile SingularAttribute<ContratoDocente, Long> cdoCodigo;
    public static volatile SingularAttribute<ContratoDocente, Short> cdoCantUnidad;
    public static volatile SingularAttribute<ContratoDocente, String> cdoTexto;
    public static volatile SingularAttribute<ContratoDocente, Long> prfCodigo;
    public static volatile SingularAttribute<ContratoDocente, Date> cdoFecini;
    public static volatile SingularAttribute<ContratoDocente, Long> cdoUsuCrea;
    public static volatile SingularAttribute<ContratoDocente, Long> asgCodigo;
    public static volatile SingularAttribute<ContratoDocente, Date> cdoFechaGenContr;
    public static volatile SingularAttribute<ContratoDocente, Long> cdoAnioAcad;
    public static volatile SingularAttribute<ContratoDocente, Long> prlCodigo;
    public static volatile SingularAttribute<ContratoDocente, Date> cdoFechaCrea;
    public static volatile SingularAttribute<ContratoDocente, Long> prgCodigo;
    public static volatile SingularAttribute<ContratoDocente, Long> cdoUsuModif;
    public static volatile SingularAttribute<ContratoDocente, String> staCodigo;
    public static volatile SingularAttribute<ContratoDocente, BigDecimal> cdoMonto;
    public static volatile SingularAttribute<ContratoDocente, Long> aspCodigo;
    public static volatile SingularAttribute<ContratoDocente, Date> cdoFecfin;
    public static volatile SingularAttribute<ContratoDocente, String> cdoRevisadoPor;
    public static volatile SingularAttribute<ContratoDocente, String> cdoNumero;
    public static volatile SingularAttribute<ContratoDocente, Date> cdoFechaModif;
    public static volatile SingularAttribute<ContratoDocente, Long> areCodigo;
    public static volatile SingularAttribute<ContratoDocente, String> cdoObservaciones;
    public static volatile SingularAttribute<ContratoDocente, RolDocente> rolDocente;
    public static volatile SingularAttribute<ContratoDocente, String> cdoDsctoUsoResid;
    public static volatile SingularAttribute<ContratoDocente, TiposFormaPago> tiposFormaPago;

}