package ec.edu.uasb.principal.entities;

import ec.edu.uasb.investigacion.entities.InveLector;
import ec.edu.uasb.investigacion.entities.InveLectorContratado;
import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.investigacion.entities.InveRecursoHumano;
import ec.edu.uasb.principal.entities.PrinBanco;
import ec.edu.uasb.principal.entities.PrinCiudad;
import ec.edu.uasb.principal.entities.PrinDiscapacidad;
import ec.edu.uasb.principal.entities.PrinPais;
import ec.edu.uasb.principal.entities.PrinTitulo;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(PrinPersona.class)
public class PrinPersona_ { 

    public static volatile SingularAttribute<PrinPersona, Long> perCodigo;
    public static volatile SingularAttribute<PrinPersona, String> perNombres;
    public static volatile SingularAttribute<PrinPersona, String> perGrupoSanguineo;
    public static volatile SingularAttribute<PrinPersona, InveLectorContratado> inveLectorContratado;
    public static volatile SingularAttribute<PrinPersona, PrinDiscapacidad> prinDiscapacidad;
    public static volatile CollectionAttribute<PrinPersona, InveLector> inveLectorCollection;
    public static volatile SingularAttribute<PrinPersona, String> perSuscripcion;
    public static volatile SingularAttribute<PrinPersona, String> perEmailTrabajo;
    public static volatile SingularAttribute<PrinPersona, String> perTipoCuenta;
    public static volatile SingularAttribute<PrinPersona, Integer> perEnrolCodigo;
    public static volatile SingularAttribute<PrinPersona, String> perTipoDoc;
    public static volatile SingularAttribute<PrinPersona, String> perUsuarioAct;
    public static volatile CollectionAttribute<PrinPersona, InveProyecto> inveProyPrimerResponCollection;
    public static volatile SingularAttribute<PrinPersona, String> perNumeroRuc;
    public static volatile SingularAttribute<PrinPersona, String> perAutoidentificacion;
    public static volatile SingularAttribute<PrinPersona, String> perNroCarnetDiscap;
    public static volatile CollectionAttribute<PrinPersona, PrinTitulo> prinTituloCollection;
    public static volatile SingularAttribute<PrinPersona, String> perDirecTrabajo;
    public static volatile SingularAttribute<PrinPersona, String> perSexo;
    public static volatile SingularAttribute<PrinPersona, String> perPrimerApellido;
    public static volatile SingularAttribute<PrinPersona, String> perOcupacionActual;
    public static volatile SingularAttribute<PrinPersona, String> perCuentaBanco;
    public static volatile SingularAttribute<PrinPersona, PrinBanco> prinBanco;
    public static volatile SingularAttribute<PrinPersona, String> perDirecDom;
    public static volatile SingularAttribute<PrinPersona, String> perEstadoCivil;
    public static volatile SingularAttribute<PrinPersona, String> perTelefonoTrab;
    public static volatile SingularAttribute<PrinPersona, PrinPais> prinNacionalidad;
    public static volatile SingularAttribute<PrinPersona, String> perTelefonoDom;
    public static volatile SingularAttribute<PrinPersona, String> perIdDoc;
    public static volatile SingularAttribute<PrinPersona, String> perCelular;
    public static volatile SingularAttribute<PrinPersona, String> perEmail;
    public static volatile CollectionAttribute<PrinPersona, InveRecursoHumano> inveRecursoHumanoCollection;
    public static volatile SingularAttribute<PrinPersona, PrinCiudad> prinCiudad;
    public static volatile SingularAttribute<PrinPersona, String> perTerceraEdad;
    public static volatile SingularAttribute<PrinPersona, Date> perFechaCrea;
    public static volatile SingularAttribute<PrinPersona, Date> perFechaNacimiento;
    public static volatile SingularAttribute<PrinPersona, String> perSegundoApellido;
    public static volatile SingularAttribute<PrinPersona, String> perUsuario;
    public static volatile SingularAttribute<PrinPersona, Date> perFechaAct;
    public static volatile SingularAttribute<PrinPersona, String> perLugarTrabajo;
    public static volatile SingularAttribute<PrinPersona, BigDecimal> perPrcDiscap;
    public static volatile SingularAttribute<PrinPersona, String> perGenero;

}