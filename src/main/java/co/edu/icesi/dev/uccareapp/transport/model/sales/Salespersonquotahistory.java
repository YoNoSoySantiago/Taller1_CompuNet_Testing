package co.edu.icesi.dev.uccareapp.transport.model.sales;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 * The persistent class for the salespersonquotahistory database table.
 *
 */
@Entity
@NamedQuery(name = "Salespersonquotahistory.findAll", query = "SELECT s FROM Salespersonquotahistory s")
public class Salespersonquotahistory implements Serializable {
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private SalespersonquotahistoryPK id;
	@Id
	@SequenceGenerator(name = "SALESPERSONQUOTAHISTORY_ID_GENERATOR", allocationSize = 1, sequenceName = "SALESPERSONQUOTAHISTORY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SALESPERSONQUOTAHISTORY_ID_GENERATOR")
	private Integer id;
	
	private Timestamp modifieddate;

	private Integer rowguid;

	private BigDecimal salesquota;

	// bi-directional many-to-one association to Salesperson
	@ManyToOne
	@JoinColumn(name = "businessentityid", insertable = false, updatable = false)
	private Salesperson salesperson;

	public Salespersonquotahistory() {
	}

//	public SalespersonquotahistoryPK getId() {
//		return this.id;
//	}
	
	public Integer getId() {
		return this.id;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public Integer getRowguid() {
		return this.rowguid;
	}

	public Salesperson getSalesperson() {
		return this.salesperson;
	}

	public BigDecimal getSalesquota() {
		return this.salesquota;
	}

//	public void setId(SalespersonquotahistoryPK id) {
//		this.id = id;
//	}
	public void setId(Integer id) {
		this.id = id;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setRowguid(Integer rowguid) {
		this.rowguid = rowguid;
	}

	public void setSalesperson(Salesperson salesperson) {
		this.salesperson = salesperson;
	}

	public void setSalesquota(BigDecimal salesquota) {
		this.salesquota = salesquota;
	}

}