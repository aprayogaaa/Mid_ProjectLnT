public class Karyawan {
	String nama;
	String kelamin;
	String jabatan;
	String id;
	double gaji;
	
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getKelamin() {
		return kelamin;
	}
	public void setKelamin(String kelamin) {
		this.kelamin = kelamin;
	}
	public String getJabatan() {
		return jabatan;
	}
	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public double getGaji() {
		return gaji;
	}
	public void setGaji(double gaji) {
		this.gaji = gaji;
	}
	
	public Karyawan(String nama, String kelamin, String jabatan, String id, double gaji) {
		super();
		this.nama = nama;
		this.kelamin = kelamin;
		this.jabatan = jabatan;
		this.id = id;
		this.gaji = gaji;
		
	}
	
	

}


