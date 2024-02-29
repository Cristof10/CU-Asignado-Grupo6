package entities;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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

import excepciones.OrigenIgualDestinoException;

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
	

	public Movimiento(Date fecha, double monto, String concepto, TipoMovimiento tipo, Cuenta origen,
			Cuenta destino, Categoria categoria) {
		super();
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

	private static boolean persistMovimiento(Movimiento movimiento) {
        EntityManager em = Persistence.createEntityManagerFactory("persistencia").createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        
        try {
            transaction.begin();
            em.persist(movimiento); // Persiste el objeto movimiento en la base de datos
            transaction.commit();
            return true; // Indica que la operación se completó correctamente
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Si ocurre un error, hace rollback de la transacción
            }
            e.printStackTrace();
            return false; // Indica que la operación no se completó correctamente
        } finally {
            em.close(); // Cierra el EntityManager
        }
    }

    public static boolean createTransferencia(Movimiento ingreso, Movimiento egreso) {
    	
    	

    	
    	
    	
    	// Realiza la transferencia de fondos
        boolean egresoExitoso = Movimiento.createGasto(egreso); // Ejecutar la operación de egreso (restar de la cuenta origen)
        if (egresoExitoso) {
            boolean ingresoExitoso = Movimiento.createIngreso(ingreso); // Ejecutar la operación de ingreso (sumar a la cuenta destino)
            if (!ingresoExitoso) {
                // Si la operación de ingreso no fue exitosa, revertir la operación de egreso
                egreso.revertEgreso();
            }
            return ingresoExitoso; // Retorna true si el ingreso fue exitoso
        } else {
            return false; // Retorna false si el egreso no fue exitoso
        }
    }

    public static boolean createGasto(Movimiento egreso) {
        // Verifica que la cuenta de origen tenga suficientes fondos
        if (egreso.origen != null && egreso.origen.getTotal() >= egreso.monto) {
            // Resta el monto de la cuenta de origen
            egreso.origen.setTotal(egreso.origen.getTotal() - egreso.monto);
            // guardar gasto 
            if(egreso.origen.persist()) {
            	persistMovimiento(egreso);
            }
            
            // Persiste los cambios en la cuenta de origen
            return egreso.origen.persist();
        } else {
            return false; // No hay suficientes fondos en la cuenta de origen
        }
    }

    public static boolean createIngreso(Movimiento ingreso) {
        // Añade el monto a la cuenta de destino
        if (ingreso.destino != null) {
            ingreso.destino.setTotal(ingreso.destino.getTotal() + ingreso.monto);
            // guardar ingreso
            if(ingreso.destino.persist()) {
            	persistMovimiento(ingreso);
            }
            // Persiste los cambios en la cuenta de destino
            return ingreso.destino.persist();
        } else {
            return false; // La cuenta de destino es inválida
        }
    }


    public void revertEgreso() {
        // Añade el monto de vuelta a la cuenta de origen
        if (origen != null) {
            origen.setTotal(origen.getTotal() + monto);
            // Actualiza la cuenta de origen en la base de datos (si es necesario)
            EntityManager em = Persistence.createEntityManagerFactory("persistencia").createEntityManager();
            em.getTransaction().begin();
            em.merge(origen);
            em.getTransaction().commit();
            em.close();
        }
    }


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