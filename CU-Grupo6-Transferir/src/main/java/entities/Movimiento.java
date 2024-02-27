package entities;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Carlos Iñiguez
 */
@Entity
@Table(name = "Movimiento")
public class Movimiento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor
	 */
	public Movimiento() {
	}
	

	public Movimiento(Integer id, Date fecha, double monto, String concepto, TipoMovimiento tipo, Cuenta origen,
			Cuenta destino, Categoria categoria) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.monto = monto;
		this.concepto = concepto;
		this.tipo = tipo;
		this.origen = origen;
		this.destino = destino;
		this.categoria = categoria;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idMovimiento")
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column
	private double monto;

	@Column
	private String concepto;

	@Enumerated
	private TipoMovimiento tipo;

	@ManyToOne
	@JoinColumn
	private Cuenta origen;

	@ManyToOne
	@JoinColumn
	private Cuenta destino;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Categoria categoria;
	



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public TipoMovimiento getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimiento tipo) {
		this.tipo = tipo;
	}

	public Cuenta getOrigen() {
		return origen;
	}

	public void setOrigen(Cuenta origen) {
		this.origen = origen;
	}

	public Cuenta getDestino() {
		return destino;
	}

	public void setDestino(Cuenta destino) {
		this.destino = destino;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * @param ingreso
	 * @return
	 */
	public static void createIngreso(Movimiento ingreso) {
		

	}

	/**
	 * @param gasto
	 * @return
	 */
	public boolean createGasto(Movimiento gasto) {
		// TODO implement here
		return false;
	}

	/**
	 * @param ingreso
	 * @param egreso
	 */
	public void createTransferencia(Movimiento ingreso, Movimiento egreso) {
		// TODO implement here
	}

	/**
	 * @param id
	 * @param fecha
	 * @param monto
	 * @param concepto
	 * @return
	 */
	public boolean update(int id, Date fecha, double monto, String concepto) {
		// TODO implement here
		return false;
	}


	public boolean delete(int id) {
		// TODO implement here
		return false;
	}

	@SuppressWarnings("unchecked")
	public static List<Movimiento> getAllByDate(Date fecha){
		EntityManager em = Persistence.createEntityManagerFactory("persistencia").createEntityManager();
    	String consultaJPQL = "SELECT t FROM Movimiento t";
    	Query query =  em.createQuery(consultaJPQL);
    	
    	
    	return (List<Movimiento>)query.getResultList();
	}

}