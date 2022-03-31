/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveLector;
import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.investigacion.entities.InveSeguimiento;
import ec.edu.uasb.portalgesdocente.entities.ContratoDocente;
import ec.edu.uasb.portalgesdocente.entities.EstadoSolicContrato;
import ec.edu.uasb.portalgesdocente.entities.EstadoSolicContratoPK;
import ec.edu.uasb.portalgesdocente.entities.RolDocente;
import ec.edu.uasb.portalgesdocente.entities.TiposFormaPago;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author victor.barba
 */
@Stateless
public class InveSeguimientoFacade extends AbstractFacade<InveSeguimiento> implements InveSeguimientoFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InveSeguimientoFacade() {
        super(InveSeguimiento.class);
    }

    @Override
    public void create(InveSeguimiento entity) {
        if (entity.getInveTipoSeguimiento().getTseCodigo().equals(new Short("10"))) {
            InveProyecto pry = entity.getInveProyecto();
            pry.setPryEstadoReg("S"); // dejar el proyecto suspenso - Código de suspensión es 10
            pry.setPryFechaEstado(new Date());
            em.merge(pry);
        } else {
            if (entity.getInveTipoSeguimiento().getTseCodigo().equals(new Short("11"))) {
                InveProyecto pry = entity.getInveProyecto();
                pry.setPryEstadoReg("A"); // reactivar el proyecto - Código de reactivación es 11
                pry.setPryFechaEstado(new Date());
                em.merge(pry);
            }
        }

        super.create(entity);
    }

    @Override
    public BigDecimal obtenerMontoxBeca(InveProyecto proyecto) {
        BigDecimal montoBeca = null;
        Query q = em.createQuery("SELECT p.preMontoAprob FROM InvePresupuesto p where p.inveRubro.rubCodigo = 7 and p.inveProyecto = :pry ");
        q.setParameter("pry", proyecto);
        try {
            montoBeca = (BigDecimal) q.getSingleResult();
        } catch (NoResultException e) {
        }
        return montoBeca;
    }

    private void createSolicContrato(InveSeguimiento seguimiento) {
        String emailCC = null;
        Query q = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // PRESUPUESTO APROBADO POR BECA DE INVESTIGACION
        BigDecimal montoBeca = obtenerMontoxBeca(seguimiento.getInveProyecto());

        //<editor-fold defaultstate="collapsed" desc="contrato">
        /*  generar registro de contrato y estado inicial */
        ContratoDocente contrato = new ContratoDocente();
        contrato.setPrfCodigo(seguimiento.getInveProyecto().getPrinPersona().getPerCodigo()); // persona contratada
        contrato.setTiposFormaPago(new TiposFormaPago(new Short("2")));
        contrato.setCdoAnioAcad(new Long(seguimiento.getInveProyecto().getPryAnioAcad()));
        contrato.setAreCodigo(new Long("17"));
        contrato.setPrgCodigo(new Long("-1"));
        contrato.setStaCodigo("S");
        contrato.setRolDocente(new RolDocente("I")); // rol para fondo de investigaciones                    
        contrato.setCdoMonto(montoBeca);
        contrato.setCdoCantUnidad(new Short("1"));
        contrato.setCdoFechaCrea(new Date());

        StringBuilder sb = new StringBuilder(" SELECT SEG_FECHA_INICIAL, SEG_FECHA_LIMITE ");
        sb.append(" from (  ")
                .append("   SELECT SEG_FECHA_INICIAL, SEG_FECHA_LIMITE, ")
                .append("   ROW_NUMBER ()OVER (PARTITION BY INVE_SEGUIMIENTO.PRY_CODIGO ORDER BY INVE_SEGUIMIENTO.SEG_FECHA_COMUNIC desc) NUM_FILA ")
                .append("   FROM INVE_SEGUIMIENTO  WHERE INVE_SEGUIMIENTO.TSE_CODIGO = 2 AND INVE_SEGUIMIENTO.SEG_ESTADO_REG ='A' and PRY_CODIGO = ? ) T ")
                .append(" WHERE T.NUM_FILA = 1  ");
        q = em.createNativeQuery(sb.toString(), "fechasContrato");
        q.setParameter(1, seguimiento.getInveProyecto().getPryCodigo());
        List temp = q.getResultList();

        for (Object record : temp) {
            try {
                Object[] reg = (Object[]) record;
                contrato.setCdoFecini(dateFormat.parse(reg[0].toString()));
                contrato.setCdoFecfin(dateFormat.parse(reg[1].toString()));
            } catch (ParseException ex) {
                Logger.getLogger(InveSeguimientoFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // usuario.-  OBTENER CODIGO DE USUARIO QUE REGISTRA TRANSACCIÓN, DESDE LA CEDULA  
        sb = new StringBuilder("select DISTINCT CODIGO, EMAIL  from V_USUARIO where CED_PAS_USUARIO = ?");
        q = em.createNativeQuery(sb.toString()).setParameter(1, seguimiento.getSegUsuarioCrea());
        List datosUsuario = q.getResultList();
        for (Object record : datosUsuario) {
            Object[] reg = (Object[]) record;
            contrato.setCdoUsuCrea(((BigDecimal) reg[0]).longValue());
            emailCC = reg[1].toString();
        }
        // FIN usuario

        em.persist(contrato);
        em.flush();

        EstadoSolicContrato esc = new EstadoSolicContrato();
        esc.setContratoDocente(contrato);
        esc.setEscCodigoUsr(contrato.getCdoUsuCrea());
        esc.setEscFecha(contrato.getCdoFechaCrea());
        esc.setEstadoSolicContratoPK(new EstadoSolicContratoPK(contrato.getCdoCodigo(), new Short("1"), "S"));
        em.persist(esc);
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="actualizar nro contrato en seguimiento">
        /*   ACTUALIZAR PROYECTO CON NUMERO DE CONTRATO  */
        seguimiento.getInveProyecto().setPryNumeroSolicContrato(contrato.getCdoCodigo());
        em.merge(seguimiento.getInveProyecto());
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="envio de correos">
        sb = new StringBuilder("SELECT distinct EMAIL from V_USUARIO ");
        sb.append(" WHERE CODIGO = (SELECT STA_FIRMA_USR_SIG_PERFIL FROM interfaseOcu.GESTIONDOCENTE.TIPO_ESTADO WHERE STA_CODIGO = 'S')");
        q = em.createNativeQuery(sb.toString()).setParameter(1, seguimiento.getSegUsuarioCrea());
        String eMailSigPerfil = (String) q.getSingleResult();

        String mensaje = this.getMessage(seguimiento);
        this.sendEmail("Soporte Servicios", "victor.barba@uasb.edu.ec", "", "comite.investigacion@uasb.edu.ec", "Solicitud de Contrato - Fondo de Investigación", mensaje, "HTML");
        //        this.sendEmail("Soporte Servicios", eMailSigPerfil, "", "comite.investigacion@uasb.edu.ec", "Solicitud de Contrato - Fondo de Investigación", mensaje, "HTML");
        //</editor-fold>

        sb = null;
        temp = null;
        q = null;
    }

    @Override
    public void create(InveSeguimiento entity, List<InveLector> listaLectorFinal, boolean aprobado) {
        if (entity.getSegCodigo() == null) {
            super.create(entity);
        }
        if (aprobado == false) { // solo en este caso se genera segundas lecturas para cada lector de informe final
            for (InveLector val : listaLectorFinal) {
                if (val.getEstadoVal().equals("2")) {
                    InveLector nuevoLector = new InveLector();
                    nuevoLector.setInveProyecto(val.getInveProyecto());
                    nuevoLector.setPrinPersona(val.getPrinPersona());
                    nuevoLector.setInveRol(val.getInveRol());
                    nuevoLector.setLecNumero((short) (val.getLecNumero() + 1));
                    nuevoLector.setLecAceptacion("S");
                    nuevoLector.setLecFechaCrea(new Date());
                    nuevoLector.setLecUsuarioCrea(entity.getSegUsuarioCrea());
                    em.persist(nuevoLector);
                }
            }
        } else {
//            POR APROBACIÓN DE INVESTIGACION SE GENERAN SOLICITUDES DE CONTRATO SOLO A LOS GRADUADOS DE MAESTRÍA Y DOCTORADO y DOCENTES CONTRATADOS
            if (entity.getInveProyecto().getPrinCategoriaInvest().getCaiCodigo() == 3 || entity.getInveProyecto().getPrinCategoriaInvest().getCaiCodigo() == 2) {
                if (entity.getInveProyecto().getPryFechaSolicContrato() != null && entity.getInveProyecto().getPryNumeroSolicContrato() == null) {
                    this.createSolicContrato(entity);   // CREAR SOLICITUD DE CONTRATO
                }
            }
        }

    }

    /**
     *
     * @param entity
     * @return
     */
    @Override
    public String getMessage(InveSeguimiento entity) {

        StringBuilder sb = new StringBuilder();
        sb.append("  SET LANGUAGE Spanish SELECT ").append("'<span style=\"font-weight:bold;padding-right:5px;").append("\">Solicitud  N°: ").append(" </span>'").append("+convert(varchar,CONTRATO_DOCENTE.CDO_CODIGO)+").append("'</br></br>'+")
                .append("'<span style=\"font-weight:bold;").append("\">Area: ").append("</span>'").append("+DEPARTAMENTO_ADMI.DEP_NOMBRE+'</br>'+")
                .append("'<span style=\"font-weight:bold;").append("\">Título de investigación: ").append("</span>'").append("+INVESTIGACION.TITULO+'</br>'+")
                .append("'<span style=\"font-weight:bold;").append("\">Investigador: ").append("</span>'").append("+PER_PRIMER_APELLIDO+ ISNULL(' '+PER_SEGUNDO_APELLIDO,'')+' '+PER_NOMBRES+'</br>'+")
                .append("'<span style=\"font-weight:bold;").append("\">Tipo de Fondo: ").append("</span>'").append("+INVESTIGACION.CAI_DESCRIPCION+").append("'</br>'+")
                .append("'<span style=\"font-weight:bold;").append("\">Inicio: ").append("</span>'").append("+convert(varchar,CONTRATO_DOCENTE.CDO_FECINI,106)+").append("'</br>'+")
                .append("'<span style=\"font-weight:bold;").append("\">Fin: ").append("</span>'").append("+convert(varchar,CONTRATO_DOCENTE.CDO_FECFIN,106)+").append("'</br>'+")
                .append("'<span style=\"font-weight:bold;").append("\">Plazo: ").append("</span>'").append("+'Investigación aprobada por el Comité de Investigaciones el '+convert(varchar,INVESTIGACION.FECHA_APROB_INFO,106)+").append("'</br></br>'+")
                .append("'Por favor, tramitar esta solicitud cuando disponga de la información requerida, en el siguiente link: '+")
                .append("'http://registro.uasb.edu.ec:8080/PortalGA'").append("+'</br></br></br>'+")
                .append("'Atentamente'+").append("'</br>Comité de Investigaciones'")
                .append(" FROM GESTIONDOCENTE.CONTRATO_DOCENTE ")
                .append(" INNER JOIN interfaseOcu.dbo.PRIN_PERSONA ON (CONTRATO_DOCENTE.PRF_CODIGO = PRIN_PERSONA.PER_CODIGO )  ")
                .append(" INNER JOIN GESTIONDOCENTE.TIPOS_FORMA_PAGO on GESTIONDOCENTE.TIPOS_FORMA_PAGO.TFP_CODIGO = CONTRATO_DOCENTE.TFP_CODIGO ")
                .append(" INNER JOIN interfaseOcu.dbo.DEPARTAMENTO_ADMI ON (CONTRATO_DOCENTE.ARE_CODIGO = DEPARTAMENTO_ADMI.DEP_CODIGO )  ")
                .append(" INNER JOIN (SELECT PRY_NUMERO_SOLIC_CONTRATO,isnull(INVE_PROYECTO.PRY_TITULO_FINAL,INVE_PROYECTO.PRY_TITULO) TITULO,PRIN_CATEGORIA_INVEST.CAI_DESCRIPCION,INFO_APROB.FECHA_APROB_INFO ")
                .append("         FROM interfaseOcu.dbo.INVE_PROYECTO ")
                .append("         INNER JOIN interfaseOcu.dbo.PRIN_CATEGORIA_INVEST ON (INVE_PROYECTO.CAI_CODIGO = PRIN_CATEGORIA_INVEST.CAI_CODIGO) ")
                .append("         INNER JOIN ( SELECT PRY_CODIGO,CAST( SEG_FECHA_COMUNIC AS DATETIME) FECHA_APROB_INFO, ROW_NUMBER ()OVER (PARTITION BY INVE_SEGUIMIENTO.PRY_CODIGO ORDER BY INVE_SEGUIMIENTO.SEG_FECHA_COMUNIC desc) NUM_FILA ")
                .append("                      FROM INVE_SEGUIMIENTO  WHERE INVE_SEGUIMIENTO.TSE_CODIGO = 3 AND INVE_SEGUIMIENTO.SEG_ESTADO_REG ='A' and INVE_SEGUIMIENTO.SEG_NOTIF_APROBADO = 'S' ) INFO_APROB")
                .append("                   ON INFO_APROB.NUM_FILA = 1 and INFO_APROB.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO ) INVESTIGACION ")
                .append("       ON (INVESTIGACION.PRY_NUMERO_SOLIC_CONTRATO = CONTRATO_DOCENTE.CDO_CODIGO) ")
                .append("  WHERE CONTRATO_DOCENTE.CDO_CODIGO = ? ");

        Query q = em.createNativeQuery(sb.toString()).setParameter(1, entity.getInveProyecto().getPryNumeroSolicContrato());
        return (String) q.getSingleResult();
    }
//     private void enviarNotificacion(InveLector entity) {
////        Map m = new HashMap();
//        StringBuilder mensaje;
//        SimpleDateFormat formatterCarta = new SimpleDateFormat("'Quito,' EEEE d 'de' MMMM 'de' yyyy", new Locale("es", "EC"));
////        // parametros de carta
////        m.put("@FECHA", formatterCarta.format(new Date()));
////        m.put("@LECTOR", entity.getPrinPersona().getPerNombres().toUpperCase()+" "+entity.getPrinPersona().getPerPrimerApellido().toUpperCase());
//        try {
//            mensaje = JsfUtil.ReadTextFile("/reportes/LectorProyecto1.docx");
//            System.out.println(mensaje.toString());
//            //mensaje = JsfUtil.ReplaceKeyText(m, mensaje);
////            this.sendEmail("Soporte Servicios",
////                    "selenia.larenas@uasb.edu.ec;fernando.andrade@uasb.edu.ec;",
////                    "", "soporteservicios@uasb.edu.ec;juancarlos.lozada@uasb.edu.ec;victor.barba@uasb.edu.ec;",
////                    "Asunto", mensaje.toString(), "HTML");
////            this.sendEmail("Soporte Servicios", "victor.barba@uasb.edu.ec", "", "", "Asignación de lector de proyecto de investigación - UASB", mensaje.toString(), "HTML");
//            mensaje = null;
//        } catch (IOException ex) {
//            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
//        }
//    }

}
