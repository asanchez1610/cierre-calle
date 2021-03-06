package pe.gob.miraflores.mobile.domain.mapaincidencias;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class MapaIncidenciaSchedule {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column GEO_MAP_INICIDENCIA_SCHEDULE.ID
     *
     * @mbggenerated Tue Sep 06 12:22:00 COT 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column GEO_MAP_INICIDENCIA_SCHEDULE.ID_INCIDENCIA
     *
     * @mbggenerated Tue Sep 06 12:22:00 COT 2016
     */
    private Integer idIncidencia;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column GEO_MAP_INICIDENCIA_SCHEDULE.HORA_INICIO
     *
     * @mbggenerated Tue Sep 06 12:22:00 COT 2016
     */
    private String horaInicio;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column GEO_MAP_INICIDENCIA_SCHEDULE.HORA_FIN
     *
     * @mbggenerated Tue Sep 06 12:22:00 COT 2016
     */
    private String horaFin;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column GEO_MAP_INICIDENCIA_SCHEDULE.DES_DIA
     *
     * @mbggenerated Tue Sep 06 12:22:00 COT 2016
     */
    private String desDia;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column GEO_MAP_INICIDENCIA_SCHEDULE.FEC_REGISTRA
     *
     * @mbggenerated Tue Sep 06 12:22:00 COT 2016
     */
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fecRegistra;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column GEO_MAP_INICIDENCIA_SCHEDULE.ESTADO
     *
     * @mbggenerated Tue Sep 06 12:22:00 COT 2016
     */
    private String estado;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column GEO_MAP_INICIDENCIA_SCHEDULE.ID
     *
     * @return the value of GEO_MAP_INICIDENCIA_SCHEDULE.ID
     *
     * @mbggenerated Tue Sep 06 12:22:00 COT 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column GEO_MAP_INICIDENCIA_SCHEDULE.ID
     *
     * @param id the value for GEO_MAP_INICIDENCIA_SCHEDULE.ID
     *
     * @mbggenerated Tue Sep 06 12:22:00 COT 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column GEO_MAP_INICIDENCIA_SCHEDULE.ID_INCIDENCIA
     *
     * @return the value of GEO_MAP_INICIDENCIA_SCHEDULE.ID_INCIDENCIA
     *
     * @mbggenerated Tue Sep 06 12:22:00 COT 2016
     */
    public Integer getIdIncidencia() {
        return idIncidencia;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column GEO_MAP_INICIDENCIA_SCHEDULE.ID_INCIDENCIA
     *
     * @param idIncidencia the value for GEO_MAP_INICIDENCIA_SCHEDULE.ID_INCIDENCIA
     *
     * @mbggenerated Tue Sep 06 12:22:00 COT 2016
     */
    public void setIdIncidencia(Integer idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column GEO_MAP_INICIDENCIA_SCHEDULE.HORA_INICIO
     *
     * @return the value of GEO_MAP_INICIDENCIA_SCHEDULE.HORA_INICIO
     *
     * @mbggenerated Tue Sep 06 12:22:00 COT 2016
     */
    public String getHoraInicio() {
        return horaInicio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column GEO_MAP_INICIDENCIA_SCHEDULE.HORA_INICIO
     *
     * @param horaInicio the value for GEO_MAP_INICIDENCIA_SCHEDULE.HORA_INICIO
     *
     * @mbggenerated Tue Sep 06 12:22:00 COT 2016
     */
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column GEO_MAP_INICIDENCIA_SCHEDULE.HORA_FIN
     *
     * @return the value of GEO_MAP_INICIDENCIA_SCHEDULE.HORA_FIN
     *
     * @mbggenerated Tue Sep 06 12:22:00 COT 2016
     */
    public String getHoraFin() {
        return horaFin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column GEO_MAP_INICIDENCIA_SCHEDULE.HORA_FIN
     *
     * @param horaFin the value for GEO_MAP_INICIDENCIA_SCHEDULE.HORA_FIN
     *
     * @mbggenerated Tue Sep 06 12:22:00 COT 2016
     */
    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column GEO_MAP_INICIDENCIA_SCHEDULE.DES_DIA
     *
     * @return the value of GEO_MAP_INICIDENCIA_SCHEDULE.DES_DIA
     *
     * @mbggenerated Tue Sep 06 12:22:00 COT 2016
     */
    public String getDesDia() {
        return desDia;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column GEO_MAP_INICIDENCIA_SCHEDULE.DES_DIA
     *
     * @param desDia the value for GEO_MAP_INICIDENCIA_SCHEDULE.DES_DIA
     *
     * @mbggenerated Tue Sep 06 12:22:00 COT 2016
     */
    public void setDesDia(String desDia) {
        this.desDia = desDia;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column GEO_MAP_INICIDENCIA_SCHEDULE.FEC_REGISTRA
     *
     * @return the value of GEO_MAP_INICIDENCIA_SCHEDULE.FEC_REGISTRA
     *
     * @mbggenerated Tue Sep 06 12:22:00 COT 2016
     */
    public Date getFecRegistra() {
        return fecRegistra;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column GEO_MAP_INICIDENCIA_SCHEDULE.FEC_REGISTRA
     *
     * @param fecRegistra the value for GEO_MAP_INICIDENCIA_SCHEDULE.FEC_REGISTRA
     *
     * @mbggenerated Tue Sep 06 12:22:00 COT 2016
     */
    public void setFecRegistra(Date fecRegistra) {
        this.fecRegistra = fecRegistra;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column GEO_MAP_INICIDENCIA_SCHEDULE.ESTADO
     *
     * @return the value of GEO_MAP_INICIDENCIA_SCHEDULE.ESTADO
     *
     * @mbggenerated Tue Sep 06 12:22:00 COT 2016
     */
    public String getEstado() {
        return estado;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column GEO_MAP_INICIDENCIA_SCHEDULE.ESTADO
     *
     * @param estado the value for GEO_MAP_INICIDENCIA_SCHEDULE.ESTADO
     *
     * @mbggenerated Tue Sep 06 12:22:00 COT 2016
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GEO_MAP_INICIDENCIA_SCHEDULE
     *
     * @mbggenerated Tue Sep 06 12:22:00 COT 2016
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MapaIncidenciaSchedule other = (MapaIncidenciaSchedule) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIdIncidencia() == null ? other.getIdIncidencia() == null : this.getIdIncidencia().equals(other.getIdIncidencia()))
            && (this.getHoraInicio() == null ? other.getHoraInicio() == null : this.getHoraInicio().equals(other.getHoraInicio()))
            && (this.getHoraFin() == null ? other.getHoraFin() == null : this.getHoraFin().equals(other.getHoraFin()))
            && (this.getDesDia() == null ? other.getDesDia() == null : this.getDesDia().equals(other.getDesDia()))
            && (this.getFecRegistra() == null ? other.getFecRegistra() == null : this.getFecRegistra().equals(other.getFecRegistra()))
            && (this.getEstado() == null ? other.getEstado() == null : this.getEstado().equals(other.getEstado()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GEO_MAP_INICIDENCIA_SCHEDULE
     *
     * @mbggenerated Tue Sep 06 12:22:00 COT 2016
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIdIncidencia() == null) ? 0 : getIdIncidencia().hashCode());
        result = prime * result + ((getHoraInicio() == null) ? 0 : getHoraInicio().hashCode());
        result = prime * result + ((getHoraFin() == null) ? 0 : getHoraFin().hashCode());
        result = prime * result + ((getDesDia() == null) ? 0 : getDesDia().hashCode());
        result = prime * result + ((getFecRegistra() == null) ? 0 : getFecRegistra().hashCode());
        result = prime * result + ((getEstado() == null) ? 0 : getEstado().hashCode());
        return result;
    }
}